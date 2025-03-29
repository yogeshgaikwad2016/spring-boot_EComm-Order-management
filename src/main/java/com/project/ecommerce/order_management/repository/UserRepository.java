package com.project.ecommerce.order_management.repository;

import com.project.ecommerce.order_management.model.Roles;
import com.project.ecommerce.order_management.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailId(String emailId);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password = :pass, u.role = :role WHERE u.emailId = :emailId")
    int updateByEmailId(@Param("emailId") String emailId, @Param("pass") String password, @Param("role") Roles role);
}
