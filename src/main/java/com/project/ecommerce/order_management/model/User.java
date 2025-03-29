package com.project.ecommerce.order_management.model;

import com.project.ecommerce.order_management.converter.PasswordConverter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(unique = true, nullable = false)
    @Email(message = "Please provide a valid email address")
    private String emailId;

    @Convert(converter = PasswordConverter.class)
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles role = Roles.ROLE_CUSTOMER;
}
