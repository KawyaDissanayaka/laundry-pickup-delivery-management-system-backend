package com.laundry.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminStatsDTO {
    private BigDecimal totalRevenue;
    private BigDecimal companyRevenue;
    private Integer activeOrders;
    private Integer activeRiders;
    private Integer newCustomers;
}
