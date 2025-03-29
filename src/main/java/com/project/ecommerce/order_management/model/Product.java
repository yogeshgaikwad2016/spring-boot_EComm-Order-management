package com.project.ecommerce.order_management.model;

import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import lombok.Data;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long productId;

    @Column(unique = true, nullable = false)
    String productName;

    String description;

    int stock = 0;

    @Column(nullable = false)
    double price;
}
