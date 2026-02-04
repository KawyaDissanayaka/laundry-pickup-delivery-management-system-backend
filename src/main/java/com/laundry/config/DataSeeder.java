package com.laundry.config;

import com.laundry.entity.User;
import com.laundry.enums.UserRole;
import com.laundry.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataSeeder implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Check if users already exist
        if (userRepository.count() > 0) {
            return; // Skip seeding if data already exists
        }
        
        // Create default users
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("password123"));
        admin.setEmail("admin@laundry.com");
        admin.setFullName("Admin User");
        admin.setPhone("0771234567");
        admin.setAddress("123 Admin Street, Colombo");
        admin.setRole(UserRole.ADMIN);
        admin.setCreatedAt(LocalDateTime.now());
        admin.setUpdatedAt(LocalDateTime.now());
        
        User customer = new User();
        customer.setUsername("customer1");
        customer.setPassword(passwordEncoder.encode("password123"));
        customer.setEmail("customer@test.com");
        customer.setFullName("Test Customer");
        customer.setPhone("0772345678");
        customer.setAddress("456 Customer Avenue, Kandy");
        customer.setRole(UserRole.CUSTOMER);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());
        
        User employee = new User();
        employee.setUsername("employee1");
        employee.setPassword(passwordEncoder.encode("password123"));
        employee.setEmail("employee@laundry.com");
        employee.setFullName("John Employee");
        employee.setPhone("0773456789");
        employee.setAddress("789 Staff Road, Galle");
        employee.setRole(UserRole.EMPLOYEE);
        employee.setCreatedAt(LocalDateTime.now());
        employee.setUpdatedAt(LocalDateTime.now());
        
        User rider = new User();
        rider.setUsername("rider1");
        rider.setPassword(passwordEncoder.encode("password123"));
        rider.setEmail("rider@laundry.com");
        rider.setFullName("Mike Rider");
        rider.setPhone("0774567890");
        rider.setAddress("321 Delivery Lane, Negombo");
        rider.setRole(UserRole.RIDER);
        rider.setCreatedAt(LocalDateTime.now());
        rider.setUpdatedAt(LocalDateTime.now());
        
        userRepository.save(admin);
        userRepository.save(customer);
        userRepository.save(employee);
        userRepository.save(rider);
        
        System.out.println("Default users seeded successfully!");
        System.out.println("Admin: username=admin, password=password123");
        System.out.println("Customer: username=customer1, password=password123");
        System.out.println("Employee: username=employee1, password=password123");
        System.out.println("Rider: username=rider1, password=password123");
    }
    
    public void seedData() {
        try {
            run();
        } catch (Exception e) {
            System.err.println("Error seeding data: " + e.getMessage());
        }
    }
}
