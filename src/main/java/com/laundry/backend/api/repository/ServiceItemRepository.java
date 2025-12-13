package com.laundry.backend.api.repository;

import com.laundry.backend.api.entity.ServiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceItemRepository extends JpaRepository<ServiceItem, String> {
}
