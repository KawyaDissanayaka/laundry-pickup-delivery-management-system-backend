package com.laundry.backend.controller;

import com.laundry.backend.model.ServiceItem;
import com.laundry.backend.service.ServiceItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "*")
public class ServiceItemController {

    @Autowired
    private ServiceItemService serviceItemService;

    @GetMapping
    public List<ServiceItem> getAllServices() {
        return serviceItemService.getAllServiceItems();
    }

    @PostMapping
    public ServiceItem addService(@RequestBody ServiceItem item) {
        return serviceItemService.addServiceItem(item);
    }
}
