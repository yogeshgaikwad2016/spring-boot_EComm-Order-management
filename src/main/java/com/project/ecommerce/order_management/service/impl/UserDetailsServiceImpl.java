package com.project.ecommerce.order_management.service.impl;

import com.project.ecommerce.order_management.exception.UserNotFound;
import com.project.ecommerce.order_management.model.LoginUserDetails;
import com.project.ecommerce.order_management.model.User;
import com.project.ecommerce.order_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    UserDetailsServiceImpl(UserRepository userRepository) {
            this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmailId(username)
                .orElseThrow(() -> new UserNotFound("User doesn't exists"));

        return new LoginUserDetails(user);
    }
}
