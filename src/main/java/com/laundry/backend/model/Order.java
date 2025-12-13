package com.laundry.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String userId;
    private List<OrderItem> items;
    private String status; // PENDING, PICKED_UP, PROCESSING, DELIVERED
    private LocalDateTime pickupDate;
    private LocalDateTime deliveryDate;
    private double totalAmount;

    @Data
    public static class OrderItem {
        private String serviceItemId;
        private String serviceName; // snapshot in case price/name changes
        private int quantity;
        private double pricePerUnit;
    }
}
