package com.project.ecommerce.order_management.exception;

import com.project.ecommerce.order_management.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<Object> handleNotFound(UserNotFound ex) {
        Map<String, Object> res = new HashMap<>();
        res.put(Constants.MESSAGE, ex.getMessage());

        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientStock.class)
    public ResponseEntity<Object> stockError(InsufficientStock ex) {
        Map<String, Object> res = new HashMap<>();
        res.put(Constants.MESSAGE, ex.getMessage());

        return new ResponseEntity<>(res, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(SONotFound.class)
    public ResponseEntity<Object> handleSONotFound(SONotFound ex) {
        Map<String, Object> res = new HashMap<>();
        res.put(Constants.MESSAGE, ex.getMessage());

        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<Object> handleProductNotFound(ProductNotFound ex) {
        Map<String, Object> body = new HashMap<>();
        body.put(Constants.MESSAGE, ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        log.info(Constants.VALIDATION_ERROR_MSG, errors);
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put(Constants.MESSAGE, ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
