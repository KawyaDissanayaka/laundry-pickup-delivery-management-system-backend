package com.laundry.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerStatsDTO {
    private Integer totalOrders;
    private Integer activeOrders;
    private Integer completedOrders;
    private BigDecimal totalSpent;
}
