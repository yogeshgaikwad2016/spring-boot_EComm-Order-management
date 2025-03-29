package com.project.ecommerce.order_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private long userId;

    @Column(unique = true)
    @Email(message = "Please provide a valid email address")
    private String emailId;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @Enumerated(EnumType.STRING)
    private Roles role;
}
