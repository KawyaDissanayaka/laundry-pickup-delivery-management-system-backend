package com.laundry.backend.model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String userId;

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItem> items;

    private String status;
    private LocalDateTime pickupDate;
    private LocalDateTime deliveryDate;
    private double totalAmount;

    @Data
    @Embeddable
    public static class OrderItem {
        private String serviceItemId;
        private String serviceName;
        private int quantity;
        private double pricePerUnit;
    }
}
