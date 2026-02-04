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
        
        // Set role (default to CUSTOMER if not specified)
        try {
            user.setRole(UserRole.valueOf(registerRequest.getRole().toUpperCase()));
        } catch (Exception e) {
            user.setRole(UserRole.CUSTOMER);
        }
        
        user = userRepository.save(user);
        
        // Generate token
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name(), user.getId());
        UserDTO userDTO = convertToUserDTO(user);
        
        return new AuthResponseDTO(token, userDTO);
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
