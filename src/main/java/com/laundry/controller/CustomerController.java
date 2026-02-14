package com.laundry.controller;

import com.laundry.dto.*;
import com.laundry.service.OrderService;
import com.laundry.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@PreAuthorize("hasRole('CUSTOMER')")
public class CustomerController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getCustomerOrders() {
        List<OrderDTO> orders = orderService.getOrdersByCustomer();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        OrderDTO order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderCreateDTO orderCreateDTO) {
        OrderDTO order = orderService.createOrder(orderCreateDTO);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/dashboard/stats")
    public ResponseEntity<CustomerStatsDTO> getCustomerStats() {
        CustomerStatsDTO stats = orderService.getCustomerStats();
        return ResponseEntity.ok(stats);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserDTO> updateProfile(@Valid @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateCurrentUser(userDTO);
        return ResponseEntity.ok(updatedUser);
    }
}
