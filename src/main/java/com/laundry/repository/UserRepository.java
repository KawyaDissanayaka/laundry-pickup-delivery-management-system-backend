package com.laundry.repository;

import com.laundry.entity.User;
import com.laundry.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);
    
    Optional<User> findByEmail(String email);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
    
    List<User> findByRole(UserRole role);
    
    @Query("SELECT COUNT(u) FROM User u WHERE u.role = :role AND u.createdAt >= :since")
    Long countByRoleAndCreatedAtAfter(UserRole role, LocalDateTime since);
    
    @Query("SELECT COUNT(u) FROM User u WHERE u.role = :role AND u.id IN " +
           "(SELECT DISTINCT o.rider.id FROM Order o WHERE o.status != 'COMPLETED')")
    Long countActiveRiders(UserRole role);


}
