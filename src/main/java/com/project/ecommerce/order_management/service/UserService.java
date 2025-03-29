package com.project.ecommerce.order_management.service;

import com.project.ecommerce.order_management.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void createUser(User user);

    List<User> getAllUsers();

    Optional<User> getUser(String emailId);
}
