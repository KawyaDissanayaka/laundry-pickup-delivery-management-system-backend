package com.laundry.service;

import com.laundry.dto.*;
import com.laundry.entity.*;
import com.laundry.enums.OrderStatus;
import com.laundry.enums.UserRole;
import com.laundry.exception.ResourceNotFoundException;
import com.laundry.exception.UnauthorizedException;
import com.laundry.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    public OrderDTO createOrder(OrderCreateDTO orderCreateDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        User customer = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        
        Order order = new Order();
        order.setCustomer(customer);
        order.setTotalAmount(orderCreateDTO.getTotalAmount());
        order.setServiceType(orderCreateDTO.getServiceType());
        order.setAddress(orderCreateDTO.getAddress());
        order.setProgressNote(orderCreateDTO.getProgressNote());
        order.setStatus(OrderStatus.PLACED);
        
        // Calculate total items
        int itemCount = orderCreateDTO.getItems().stream()
                .mapToInt(OrderItemDTO::getQuantity)
                .sum();
        order.setItemCount(itemCount);
        
        order = orderRepository.save(order);
        
        // Save order items
        for (OrderItemDTO itemDTO : orderCreateDTO.getItems()) {
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setName(itemDTO.getName());
            item.setService(itemDTO.getService());
            item.setQuantity(itemDTO.getQuantity());
            item.setPrice(itemDTO.getPrice());
            orderItemRepository.save(item);
        }
        
        return convertToOrderDTO(order);
    }
    
    public List<OrderDTO> getOrdersByCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        User customer = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        
        List<Order> orders = orderRepository.findByCustomerId(customer.getId());
        return orders.stream()
                .map(this::convertToOrderDTO)
                .collect(Collectors.toList());
    }
    
    public List<OrderDTO> getOrdersByEmployee() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        User employee = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        
        List<Order> orders = orderRepository.findByEmployeeId(employee.getId());
        return orders.stream()
                .map(this::convertToOrderDTO)
                .collect(Collectors.toList());
    }
    
    public List<OrderDTO> getOrdersByRider() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        User rider = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Rider not found"));
        
        List<Order> orders = orderRepository.findByRiderId(rider.getId());
        return orders.stream()
                .map(this::convertToOrderDTO)
                .collect(Collectors.toList());
    }
    
    public List<OrderDTO> getUnassignedPlacedOrders() {
        List<Order> orders = orderRepository.findByStatusAndRiderIsNull(OrderStatus.PLACED);
        return orders.stream()
                .map(this::convertToOrderDTO)
                .collect(Collectors.toList());
    }
    
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::convertToOrderDTO)
                .collect(Collectors.toList());
    }
    
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", id));
        
        // Check authorization based on user role
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        if (currentUser.getRole() == UserRole.CUSTOMER && !order.getCustomer().getId().equals(currentUser.getId())) {
            throw new UnauthorizedException("You can only view your own orders");
        }
        
        if (currentUser.getRole() == UserRole.EMPLOYEE && !order.getEmployee().getId().equals(currentUser.getId())) {
            throw new UnauthorizedException("You can only view orders assigned to you");
        }
        
        if (currentUser.getRole() == UserRole.RIDER && !order.getRider().getId().equals(currentUser.getId())) {
            throw new UnauthorizedException("You can only view orders assigned to you");
        }
        
        return convertToOrderDTO(order);
    }
    
    public OrderDTO updateOrderStatus(Long id, String status, String role) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", id));
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        // Check authorization
        if (currentUser.getRole() == UserRole.EMPLOYEE && !order.getEmployee().getId().equals(currentUser.getId())) {
            throw new UnauthorizedException("You can only update orders assigned to you");
        }
        
        if (currentUser.getRole() == UserRole.RIDER && !order.getRider().getId().equals(currentUser.getId())) {
            throw new UnauthorizedException("You can only update orders assigned to you");
        }
        
        try {
            OrderStatus newStatus = OrderStatus.valueOf(status.toUpperCase());
            order.setStatus(newStatus);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }
        
        order = orderRepository.save(order);
        return convertToOrderDTO(order);
    }
    
    public OrderDTO updateOrderProgress(Long id, String progressNote) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", id));
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        // Check authorization
        if (currentUser.getRole() == UserRole.EMPLOYEE && !order.getEmployee().getId().equals(currentUser.getId())) {
            throw new UnauthorizedException("You can only update orders assigned to you");
        }
        
        order.setProgressNote(progressNote);
        order = orderRepository.save(order);
        return convertToOrderDTO(order);
    }
    
    public OrderDTO assignEmployee(Long orderId, Long employeeId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));
        
        User employee = userRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", employeeId));
        
        if (employee.getRole() != UserRole.EMPLOYEE) {
            throw new IllegalArgumentException("User is not an employee");
        }
        
        order.setEmployee(employee);
        order.setStatus(OrderStatus.ASSIGNED);
        order = orderRepository.save(order);
        
        return convertToOrderDTO(order);
    }
    
    public OrderDTO assignOrderToRider(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User rider = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Rider not found"));
        
        // Only allow assignment if order is PLACED and not assigned
        if (order.getStatus() != OrderStatus.PLACED || order.getRider() != null) {
            throw new IllegalArgumentException("Order is not available for pickup");
        }
        
        // Assign order to rider and change status
        order.setRider(rider);
        order.setStatus(OrderStatus.ASSIGNED);
        order = orderRepository.save(order);
        
        return convertToOrderDTO(order);
    }
    
    public OrderDTO assignRider(Long orderId, Long riderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));
        
        User rider = userRepository.findById(riderId)
                .orElseThrow(() -> new ResourceNotFoundException("Rider", riderId));
        
        if (rider.getRole() != UserRole.RIDER) {
            throw new IllegalArgumentException("User is not a rider");
        }
        
        order.setRider(rider);
        order.setStatus(OrderStatus.ASSIGNED);
        order = orderRepository.save(order);
        
        return convertToOrderDTO(order);
    }
    
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", id));
        
        orderRepository.delete(order);
    }
    
    public CustomerStatsDTO getCustomerStats() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        User customer = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        
        Long customerId = customer.getId();
        
        Integer totalOrders = orderRepository.findByCustomerId(customerId).size();
        Long activeOrdersCount = orderRepository.countActiveOrdersByCustomer(customerId);
        Integer activeOrders = activeOrdersCount.intValue();
        Integer completedOrders = totalOrders - activeOrders;
        BigDecimal totalSpent = orderRepository.getTotalSpentByCustomer(customerId);
        
        if (totalSpent == null) totalSpent = BigDecimal.ZERO;
        
        return new CustomerStatsDTO(totalOrders, activeOrders, completedOrders, totalSpent);
    }
    
    public RiderStatsDTO getRiderStats() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        User rider = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Rider not found"));
        
        Long riderId = rider.getId();
        
        Long pendingOrdersCount = orderRepository.countActiveOrdersByRider(riderId);
        Integer pendingOrders = pendingOrdersCount.intValue();
        BigDecimal commission = orderRepository.getCommissionByRider(riderId);
        
        if (commission == null) commission = BigDecimal.ZERO;
        
        return new RiderStatsDTO(pendingOrders, commission);
    }
    
    public AdminStatsDTO getAdminStats() {
        BigDecimal totalRevenue = orderRepository.getTotalRevenue();
        if (totalRevenue == null) totalRevenue = BigDecimal.ZERO;
        
        BigDecimal companyRevenue = totalRevenue.multiply(new BigDecimal("0.95")); // 95% goes to company
        Long activeOrdersCount = orderRepository.countActiveOrders();
        Integer activeOrders = activeOrdersCount.intValue();
        
        Long activeRidersCount = userRepository.countActiveRiders(UserRole.RIDER);
        Integer activeRiders = activeRidersCount.intValue();
        
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        Long newCustomersCount = userRepository.countByRoleAndCreatedAtAfter(UserRole.CUSTOMER, thirtyDaysAgo);
        Integer newCustomers = newCustomersCount.intValue();
        
        return new AdminStatsDTO(totalRevenue, companyRevenue, activeOrders, activeRiders, newCustomers);
    }
    
    private OrderDTO convertToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCustomer(convertToUserDTO(order.getCustomer()));
        orderDTO.setEmployee(order.getEmployee() != null ? convertToUserDTO(order.getEmployee()) : null);
        orderDTO.setRider(order.getRider() != null ? convertToUserDTO(order.getRider()) : null);
        orderDTO.setItems(order.getItems().stream()
                .map(this::convertToOrderItemDTO)
                .collect(Collectors.toList()));
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setStatus(order.getStatus().name());
        orderDTO.setServiceType(order.getServiceType());
        orderDTO.setItemCount(order.getItemCount());
        orderDTO.setAddress(order.getAddress());
        orderDTO.setProgressNote(order.getProgressNote());
        orderDTO.setCreatedAt(order.getCreatedAt() != null ? order.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME) : null);
        orderDTO.setCompletedAt(order.getCompletedAt() != null ? order.getCompletedAt().format(DateTimeFormatter.ISO_DATE_TIME) : null);
        return orderDTO;
    }
    
    private UserDTO convertToUserDTO(User user) {
        if (user == null) return null;
        
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setFullName(user.getFullName());
        userDTO.setPhone(user.getPhone());
        userDTO.setAddress(user.getAddress());
        userDTO.setRole(user.getRole().name().toLowerCase());
        return userDTO;
    }
    
    private OrderItemDTO convertToOrderItemDTO(OrderItem item) {
        OrderItemDTO itemDTO = new OrderItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setService(item.getService());
        itemDTO.setQuantity(item.getQuantity());
        itemDTO.setPrice(item.getPrice());
        return itemDTO;
    }
}
