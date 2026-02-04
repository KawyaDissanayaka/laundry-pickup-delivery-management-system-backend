package com.laundry.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private UserDTO customer;
    private UserDTO employee;
    private UserDTO rider;
    private List<OrderItemDTO> items;
    private java.math.BigDecimal totalAmount;
    private String status;
    private String serviceType;
    private Integer itemCount;
    private String address;
    private String progressNote;
    private String createdAt;
    private String completedAt;
}
