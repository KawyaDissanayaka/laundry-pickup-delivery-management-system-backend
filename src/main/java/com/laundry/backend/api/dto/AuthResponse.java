package com.laundry.backend.api.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String role;
    private String message;

    public AuthResponse(String message) {
        this.message = message;
    }

    public AuthResponse() {
    }
}
