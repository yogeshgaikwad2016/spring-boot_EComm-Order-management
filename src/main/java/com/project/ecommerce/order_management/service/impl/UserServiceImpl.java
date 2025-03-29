package com.project.ecommerce.order_management.service.impl;

import com.project.ecommerce.order_management.exception.UserNotFound;
import com.project.ecommerce.order_management.model.User;
import com.project.ecommerce.order_management.repository.UserRepository;
import com.project.ecommerce.order_management.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUser(String emailId) {
        return userRepository.findByEmailId(emailId);
    }

    @Override
    public void updateUser(User user) {
        getUser(user.getEmailId())
                .orElseThrow(() -> new UserNotFound("User with email id - " + user.getEmailId() + " is not found, Please create new"));
        int rowCount = userRepository.updateByEmailId(user.getEmailId(), user.getPassword(), user.getRole());
        log.info("Updated user {} and total rows affected are :- {}", user.getEmailId(), rowCount);
    }
}
