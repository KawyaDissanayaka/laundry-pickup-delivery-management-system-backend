package com.laundry.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateDTO {
    
    @Valid
    @NotEmpty(message = "Items list cannot be empty")
    private List<OrderItemDTO> items;
    
    @NotNull(message = "Total amount is required")
    @DecimalMin(value = "0.01", message = "Total amount must be greater than 0")
    private BigDecimal totalAmount;
    
    @NotBlank(message = "Service type is required")
    private String serviceType;
    
    @NotBlank(message = "Address is required")
    private String address;
    
    private String progressNote;
}
