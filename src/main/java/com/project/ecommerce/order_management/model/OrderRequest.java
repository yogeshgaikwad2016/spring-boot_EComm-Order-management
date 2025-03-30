package com.project.ecommerce.order_management.model;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class OrderRequest {
    @NonNull
    private String emailId;

    @NonNull
    private List<OrderItemRequest> items;
}
