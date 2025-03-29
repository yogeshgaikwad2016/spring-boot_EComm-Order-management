package com.project.ecommerce.order_management.service.impl;

import com.mysql.cj.util.StringUtils;
import com.project.ecommerce.order_management.model.Roles;
import com.project.ecommerce.order_management.model.User;
import com.project.ecommerce.order_management.repository.UserRepository;
import com.project.ecommerce.order_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {  // <-- Use PasswordEncoder
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(User user) {
        if (user.getRole() == null)
            user.setRole(Roles.ROLE_CUSTOMER);

        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash password before saving
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
}
