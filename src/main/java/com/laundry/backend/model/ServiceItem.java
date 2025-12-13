package com.laundry.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "service_items")
public class ServiceItem {
    @Id
    private String id;
    private String name;
    private double price;
    private String description;
    private String category; // WASH_FOLD, DRY_CLEAN, IRON_ONLY
}
