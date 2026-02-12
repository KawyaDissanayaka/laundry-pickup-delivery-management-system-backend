package com.laundry.repository;

import com.laundry.entity.Order;
import com.laundry.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    List<Order> findByCustomerId(Long customerId);
    
    List<Order> findByEmployeeId(Long employeeId);
    
    List<Order> findByRiderId(Long riderId);
    
    List<Order> findByStatus(OrderStatus status);
    
    List<Order> findByStatusAndRiderIsNull(OrderStatus status);
    
    List<Order> findByStatusIn(List<OrderStatus> statuses);
    
    @Query("SELECT COUNT(o) FROM Order o WHERE o.customer.id = :customerId AND o.status != 'COMPLETED'")
    Long countActiveOrdersByCustomer(@Param("customerId") Long customerId);
    
    @Query("SELECT COUNT(o) FROM Order o WHERE o.employee.id = :employeeId AND o.status != 'COMPLETED'")
    Long countActiveOrdersByEmployee(@Param("employeeId") Long employeeId);
    
    @Query("SELECT COUNT(o) FROM Order o WHERE o.rider.id = :riderId AND o.status != 'COMPLETED'")
    Long countActiveOrdersByRider(@Param("riderId") Long riderId);
    
    @Query("SELECT COUNT(o) FROM Order o WHERE o.employee.id = :employeeId AND o.status = 'COMPLETED' AND o.completedAt >= :since")
    Long countCompletedOrdersByEmployeeSince(@Param("employeeId") Long employeeId, @Param("since") LocalDateTime since);
    
    @Query("SELECT COUNT(o) FROM Order o WHERE o.rider.id = :riderId AND o.status = 'COMPLETED'")
    Long countCompletedOrdersByRider(@Param("riderId") Long riderId);
    
    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.customer.id = :customerId AND o.status = 'COMPLETED'")
    BigDecimal getTotalSpentByCustomer(@Param("customerId") Long customerId);
    
    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.status = 'COMPLETED'")
    BigDecimal getTotalRevenue();
    
    @Query("SELECT SUM(o.totalAmount * 0.05) FROM Order o WHERE o.rider.id = :riderId AND o.status = 'COMPLETED'")
    BigDecimal getCommissionByRider(@Param("riderId") Long riderId);
    
    @Query("SELECT COUNT(o) FROM Order o WHERE o.status != 'COMPLETED'")
    Long countActiveOrders();
}
