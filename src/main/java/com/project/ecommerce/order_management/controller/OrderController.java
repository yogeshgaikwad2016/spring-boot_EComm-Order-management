package com.project.ecommerce.order_management.controller;

import com.mysql.cj.util.StringUtils;
import com.project.ecommerce.order_management.model.OrderRequest;
import com.project.ecommerce.order_management.model.OrderStatus;
import com.project.ecommerce.order_management.service.OrderService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
        if (StringUtils.isNullOrEmpty(salesOrderId)) {
            log.error("SO ID is mandatory.");
            return ResponseEntity.badRequest().body("SO ID is mandatory.");
        }
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

    @PutMapping("/order/status/{salesOrderId}")
    ResponseEntity<?> updateSOStatus(@PathVariable String salesOrderId, @RequestParam OrderStatus status){
        if (StringUtils.isNullOrEmpty(salesOrderId) || status == null) {
            log.error("SO ID and Status are mandatory.");
            return ResponseEntity.badRequest().body("SO ID and Status are mandatory.");
        }
        return ResponseEntity.ok(orderService.updateOrderStatus(salesOrderId, status));
    }

    @DeleteMapping("/order/{salesOrderID}")
    ResponseEntity<?> deleteSO(@PathVariable String salesOrderId) {
        if (StringUtils.isNullOrEmpty(salesOrderId)) {
            log.error("SO ID is mandatory.");
            return ResponseEntity.badRequest().body("SO ID is mandatory.");
        }
        orderService.deleteSO(salesOrderId);
        return ResponseEntity.ok(salesOrderId + " Deleted successfully");
    }
}
