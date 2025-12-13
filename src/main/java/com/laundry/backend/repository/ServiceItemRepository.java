package com.laundry.backend.repository;

import com.laundry.backend.model.ServiceItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServiceItemRepository extends MongoRepository<ServiceItem, String> {
}
