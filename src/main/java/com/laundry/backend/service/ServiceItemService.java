package com.laundry.backend.service;

import com.laundry.backend.model.ServiceItem;
import com.laundry.backend.repository.ServiceItemRepository;
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
