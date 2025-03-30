package com.project.ecommerce.order_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SONotFound extends RuntimeException{
    public SONotFound(String message) {
        super(message);
    }
}
