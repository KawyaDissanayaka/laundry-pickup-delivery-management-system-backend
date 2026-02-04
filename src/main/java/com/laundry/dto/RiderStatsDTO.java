package com.laundry.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RiderStatsDTO {
    private Integer pendingOrders;
    private BigDecimal commission;
}
