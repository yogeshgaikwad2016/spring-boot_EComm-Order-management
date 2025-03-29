package com.project.ecommerce.order_management.controller;

import com.project.ecommerce.order_management.exception.ErrorResponse;
import com.project.ecommerce.order_management.exception.UserNotFound;
import com.project.ecommerce.order_management.model.User;
import com.project.ecommerce.order_management.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
         this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserDetails(@RequestParam String emailId) {
        if (emailId == null || emailId.isBlank()) {
            log.error("E-Mail id is mandatory, please provide email id.");
            return ResponseEntity.badRequest().body(new ErrorResponse("E-Mail id is empty, please provide email id."));
        }

        User user = userService.getUser(emailId)
                .orElseThrow(() -> new UserNotFound("User with email id - " + emailId + " is not found, Please create new"));

        if (user == null) {
            log.error("User + {} not found", emailId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("User not found"));
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    List<?> getAllUserDetails() {
        List<User> users =  userService.getAllUsers();

        if (users == null || users.isEmpty()) {
            return Collections.singletonList(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No user is created")));
        }

        return ResponseEntity.ok(users).getBody();
    }

    @PostMapping("/user")
    ResponseEntity<String> createUser(@RequestBody User user, BindingResult result) {

        if (result.hasErrors()) {
            log.error("Validation failed for field - {}", result.getTarget());
            return ResponseEntity.badRequest().body("Validation failed");
        }

        userService.createUser(user);

        log.info("User {} created successfully.", user.getEmailId());
        return ResponseEntity.ok("User with emailID: " + user.getEmailId() + " is created");
    }
}
