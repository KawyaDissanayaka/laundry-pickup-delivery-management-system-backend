package com.laundry.controller;

import com.laundry.dto.OrderDTO;
import com.laundry.dto.RiderStatsDTO;
import com.laundry.enums.OrderStatus;
import com.laundry.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rider")
@PreAuthorize("hasRole('RIDER')")
public class RiderController {
    
    @Autowired
    private OrderService orderService;
    
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getRiderOrders() {
        List<OrderDTO> orders = orderService.getOrdersByRider();
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
        
        OrderDTO order = orderService.updateOrderStatus(id, status, "rider");
        return ResponseEntity.ok(order);
    }
    
    @PostMapping("/orders/{id}/confirm-pickup")
    public ResponseEntity<OrderDTO> confirmPickup(@PathVariable Long id) {
        // When rider confirms pickup, change status to AT_LAUNDRY
        OrderDTO order = orderService.updateOrderStatus(id, "AT_LAUNDRY", "rider");
        return ResponseEntity.ok(order);
    }
    
    @GetMapping("/dashboard/stats")
    public ResponseEntity<RiderStatsDTO> getRiderStats() {
        RiderStatsDTO stats = orderService.getRiderStats();
        return ResponseEntity.ok(stats);
    }
    
    @GetMapping("/dashboard/pending-pickup")
    public ResponseEntity<List<OrderDTO>> getPendingPickupOrders() {
        List<OrderDTO> orders = orderService.getOrdersByRider();
        List<OrderDTO> pendingPickupOrders = orders.stream()
                .filter(order -> OrderStatus.PLACED.name().equals(order.getStatus()))
                .toList();
        return ResponseEntity.ok(pendingPickupOrders);
    }
}
