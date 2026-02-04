package com.laundry.controller;

import com.laundry.dto.AuthRequestDTO;
import com.laundry.dto.AuthResponseDTO;
import com.laundry.dto.RegisterRequestDTO;
import com.laundry.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000", "http://localhost:5174"})
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO authRequest) {
        try {
            System.out.println("=== LOGIN REQUEST RECEIVED ===");
            System.out.println("Username: " + authRequest.getUsername());
            AuthResponseDTO response = authService.login(authRequest);
            return ResponseEntity.ok(response);
        }

        catch (Exception e) {
            System.err.println("=== LOGIN ERROR ===");
            e.printStackTrace();
            return ResponseEntity.status(500).body("Login failed: " + e.getMessage());
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody RegisterRequestDTO registerRequest) {
        AuthResponseDTO response = authService.register(registerRequest);
        return ResponseEntity.ok(response);
    }
}
