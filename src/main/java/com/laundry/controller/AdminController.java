package com.laundry.controller;

import com.laundry.dto.*;
import com.laundry.enums.OrderStatus;
import com.laundry.service.OrderService;
import com.laundry.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000", "http://localhost:5174"})
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private UserService userService;
    
    // Order Management Endpoints
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        OrderDTO order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
    
    @PutMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> updateOrder(
            @PathVariable Long id, 
            @Valid @RequestBody OrderCreateDTO orderCreateDTO) {
        
        // For now, we'll implement a simple update
        OrderDTO existingOrder = orderService.getOrderById(id);
        // In a real implementation, you would update all fields
        return ResponseEntity.ok(existingOrder);
    }
    
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/orders/{id}/assign-employee")
    public ResponseEntity<OrderDTO> assignEmployee(
            @PathVariable Long id, 
            @RequestBody Map<String, Long> request) {
        
        Long employeeId = request.get("employeeId");
        if (employeeId == null) {
            throw new IllegalArgumentException("Employee ID is required");
        }
        
        OrderDTO order = orderService.assignEmployee(id, employeeId);
        return ResponseEntity.ok(order);
    }
    
    @PostMapping("/orders/{id}/assign-rider")
    public ResponseEntity<OrderDTO> assignRider(
            @PathVariable Long id, 
            @RequestBody Map<String, Long> request) {
        
        Long riderId = request.get("riderId");
        if (riderId == null) {
            throw new IllegalArgumentException("Rider ID is required");
        }
        
        OrderDTO order = orderService.assignRider(id, riderId);
        return ResponseEntity.ok(order);
    }
    
    @PutMapping("/orders/{id}/status")
    public ResponseEntity<OrderDTO> updateOrderStatus(
            @PathVariable Long id, 
            @RequestBody Map<String, String> statusUpdate) {
        
        String status = statusUpdate.get("status");
        if (status == null) {
            throw new IllegalArgumentException("Status is required");
        }
        
        OrderDTO order = orderService.updateOrderStatus(id, status, "admin");
        return ResponseEntity.ok(order);
    }
    
    @GetMapping("/orders/status/{status}")
    public ResponseEntity<List<OrderDTO>> getOrdersByStatus(@PathVariable String status) {
        List<OrderDTO> allOrders = orderService.getAllOrders();
        List<OrderDTO> filteredOrders = allOrders.stream()
                .filter(order -> status.equals(order.getStatus()))
                .toList();
        return ResponseEntity.ok(filteredOrders);
    }
    
    // Analytics Endpoints
    @GetMapping("/analytics/stats")
    public ResponseEntity<AdminStatsDTO> getAdminStats() {
        AdminStatsDTO stats = orderService.getAdminStats();
        return ResponseEntity.ok(stats);
    }
    
    // User Management Endpoints
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    @PostMapping("/employees")
    public ResponseEntity<UserDTO> createEmployee(@Valid @RequestBody UserDTO userDTO) {
        UserDTO employee = userService.createEmployee(userDTO);
        return ResponseEntity.ok(employee);
    }
    
    @PostMapping("/riders")
    public ResponseEntity<UserDTO> createRider(@Valid @RequestBody UserDTO userDTO) {
        UserDTO rider = userService.createRider(userDTO);
        return ResponseEntity.ok(rider);
    }
    @GetMapping("/employees")
    public ResponseEntity<List<UserDTO>> getAllEmployees() {
        List<UserDTO> employees = userService.getUsersByRole("EMPLOYEE");
        return ResponseEntity.ok(employees);
    }


}
