package com.project.ecommerce.order_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFound extends RuntimeException{

    public ProductNotFound(String message) {
        super(message);
    }
}
