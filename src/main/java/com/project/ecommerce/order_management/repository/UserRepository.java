package com.project.ecommerce.order_management.repository;

import com.project.ecommerce.order_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmailId(String emailId);
}
