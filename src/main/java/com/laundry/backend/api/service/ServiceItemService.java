package com.laundry.backend.api.service;

import com.laundry.backend.api.entity.ServiceItem;
import com.laundry.backend.api.repository.ServiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServiceItemService {

    @Autowired
    private ServiceItemRepository serviceItemRepository;

    public List<ServiceItem> getAllServiceItems() {
        return serviceItemRepository.findAll();
    }

    public ServiceItem addServiceItem(ServiceItem item) {
        return serviceItemRepository.save(item);
    }
}
