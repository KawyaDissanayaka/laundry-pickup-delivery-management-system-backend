package com.laundry.service;

import com.laundry.dto.AuthRequestDTO;
import com.laundry.dto.AuthResponseDTO;
import com.laundry.dto.RegisterRequestDTO;
import com.laundry.dto.UserDTO;
import com.laundry.entity.User;
import com.laundry.enums.UserRole;
import com.laundry.exception.ResourceNotFoundException;
import com.laundry.repository.UserRepository;
import com.laundry.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponseDTO login(AuthRequestDTO authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        User user = userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String token = jwtUtil.generateToken(userDetails);
        UserDTO userDTO = convertToUserDTO(user);

        return new AuthResponseDTO(token, userDTO);
    }

    public AuthResponseDTO register(RegisterRequestDTO registerRequest) {
        // Check if username already exists
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Check if email already exists
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Create new user
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setFullName(registerRequest.getFullName());
        user.setPhone(registerRequest.getPhone());
        user.setAddress(registerRequest.getAddress());

        // Determine role based on email domain (PRIMARY METHOD)
        UserRole assignedRole = determineRoleFromEmail(registerRequest.getEmail());
        user.setRole(assignedRole);

        // Log the role assignment for debugging
        System.out.println("User registered: " + user.getUsername() +
                " (Email: " + user.getEmail() + ") → Role: " + assignedRole);

        user = userRepository.save(user);

        // Generate token
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name(), user.getId());
        UserDTO userDTO = convertToUserDTO(user);

        return new AuthResponseDTO(token, userDTO);
    }

    /**
     * Determines user role based on email domain
     * @param email User's email address
     * @return UserRole based on email domain
     */
    private UserRole determineRoleFromEmail(String email) {
        if (email == null || !email.contains("@")) {
            System.out.println("Invalid email format, defaulting to CUSTOMER");
            return UserRole.CUSTOMER;
        }

        String emailLower = email.toLowerCase().trim();
        System.out.println("Determining role for email: " + emailLower);

        // Extract domain (everything after @)
        String domain = emailLower.substring(emailLower.indexOf("@") + 1);
        System.out.println("Email domain: " + domain);

        // Role assignment based on domain
        UserRole role;
        if (emailLower.endsWith("@admin.com")) {
            role = UserRole.ADMIN;
            System.out.println("Matched @admin.com → ADMIN");
        } else if (emailLower.endsWith("@rider.com")) {
            role = UserRole.RIDER;
            System.out.println("Matched @rider.com → RIDER");
        } else if (emailLower.endsWith("@staff.com")) {
            role = UserRole.EMPLOYEE;
            System.out.println("Matched @staff.com → EMPLOYEE");
        } else {
            role = UserRole.CUSTOMER;
            System.out.println("No domain match → CUSTOMER (default)");
        }

        return role;
    }

    private UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setFullName(user.getFullName());
        userDTO.setPhone(user.getPhone());
        userDTO.setAddress(user.getAddress());
        userDTO.setRole(user.getRole().name().toLowerCase());
        return userDTO;
    }
}