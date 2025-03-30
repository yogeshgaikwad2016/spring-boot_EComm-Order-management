package com.project.ecommerce.order_management.controller;

import com.project.ecommerce.order_management.model.OrderRequest;
import com.project.ecommerce.order_management.model.SalesOrder;
import com.project.ecommerce.order_management.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    OrderController(OrderService orderService) {
        this.orderService  = orderService;
    }

    @GetMapping("/order/{salesOrderId}")
    ResponseEntity<?> getSO(@PathVariable String salesOrderId) {
        return ResponseEntity.ok(orderService.getSO(salesOrderId));
    }

    @GetMapping("/orders")
    ResponseEntity<List<?>> getAllSO() {
        return ResponseEntity.ok(orderService.getAllSO());
    }

    @PostMapping("/order")
    ResponseEntity<?> placeSalesOrder(@Valid @RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.placeSO(orderRequest));
    }

    @PutMapping("/order")
    ResponseEntity<?> updateSalesOrder(@Valid @RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.placeSO(orderRequest));
    }

    @DeleteMapping("/order/{salesOrderID}")
    ResponseEntity<?> deleteSO(@PathVariable String salesOrderId) {
        orderService.deleteSO(salesOrderId);
        return ResponseEntity.ok(salesOrderId + " Deleted successfully");
    }
}
