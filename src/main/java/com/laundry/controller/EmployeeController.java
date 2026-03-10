package com.laundry.controller;

import com.laundry.dto.OrderDTO;
import com.laundry.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
@PreAuthorize("hasRole('EMPLOYEE')")
public class EmployeeController {
    
    @Autowired
    private OrderService orderService;
    
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getEmployeeOrders() {
        List<OrderDTO> orders = orderService.getOrdersByEmployee();
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        OrderDTO order = orderService.getOrderById(id);
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
        
        OrderDTO order = orderService.updateOrderStatus(id, status, "employee");
        return ResponseEntity.ok(order);
    }
    
    @PutMapping("/orders/{id}/progress")
    public ResponseEntity<OrderDTO> updateOrderProgress(
            @PathVariable Long id, 
            @RequestBody Map<String, String> progressUpdate) {
        
        String progressNote = progressUpdate.get("progressNote");
        if (progressNote == null) {
            progressNote = "";
        }
        
        OrderDTO order = orderService.updateOrderProgress(id, progressNote);
        return ResponseEntity.ok(order);
    }
    
    @GetMapping("/dashboard/stats")
    public ResponseEntity<Map<String, Object>> getEmployeeStats() {
        // Get employee's orders to calculate stats
        List<OrderDTO> orders = orderService.getOrdersByEmployee();
        
        long pendingOrders = orders.stream()
                .filter(order -> !"COMPLETED".equals(order.getStatus()))
                .count();
        
        long processedToday = orders.stream()
                .filter(order -> "COMPLETED".equals(order.getStatus()))
                .count();
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("pendingOrders", pendingOrders);
        stats.put("processedToday", processedToday);
        
        return ResponseEntity.ok(stats);
    }
}
