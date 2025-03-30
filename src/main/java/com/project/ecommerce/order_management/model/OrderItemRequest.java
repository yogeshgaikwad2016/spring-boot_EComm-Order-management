package com.project.ecommerce.order_management.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class OrderItemRequest {
    @NonNull
    private String productName;

    private int quantity;
}
