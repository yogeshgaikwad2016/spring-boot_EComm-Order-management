package com.project.ecommerce.order_management.service;

import com.project.ecommerce.order_management.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void createUser(User user);

    List<User> getAllUsers();

    User getUser(String emailId);

    void updateUser(User user);
}
