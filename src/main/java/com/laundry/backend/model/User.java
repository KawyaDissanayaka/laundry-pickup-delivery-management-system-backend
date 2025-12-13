package com.laundry.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String password; // In a real app, this should be hashed
    private String phone;
    private String role; // CUSTOMER, ADMIN
}
