package com.project.ecommerce.order_management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.ecommerce.order_management.converter.PasswordConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<SalesOrder> salesOrders;
}
