package com.project.ecommerce.order_management.exception;

import org.springframework.beans.factory.annotation.Autowired;

public record ErrorResponse(String message) {
    @Autowired
    public ErrorResponse {
    }
}
