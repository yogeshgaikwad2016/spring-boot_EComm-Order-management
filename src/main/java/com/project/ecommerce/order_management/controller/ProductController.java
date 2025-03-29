package com.project.ecommerce.order_management.controller;

import com.mysql.cj.util.StringUtils;
import com.project.ecommerce.order_management.model.Product;
import com.project.ecommerce.order_management.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/")
public class ProductController {

    private final ProductService productService;

    @Autowired
    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    ResponseEntity<List<?>> getAllProducts() {
        List<Product> products = productService.getAllProducts();

        if(products.isEmpty()) {
            log.info("No products exists.");
            return ResponseEntity.ok(Collections.singletonList("No products presents, Please add."));
        }

        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/{productName}")
    ResponseEntity<?> getProduct(@PathVariable("productName") String productName) {
        if (StringUtils.isNullOrEmpty(productName)) {
            log.error("Product name cannot be empty or null");
            return ResponseEntity.badRequest().body("Product name cannot be empty");
        }

        return ResponseEntity.ok(productService.getProduct(productName));
    }

    @PostMapping("/product")
    ResponseEntity<?> addProduct(@Valid @RequestBody Product product) {
        Product savedProd = productService.saveProduct(product);
        log.info("{} Saved successfully.", product);
        return ResponseEntity.ok(savedProd);
    }

    @PutMapping("/product")
    ResponseEntity<?> updateProduct(@Valid @RequestBody Product product) {
        productService.updateProduct(product);
        log.info("{} is updated successfully", product.getProductName());
        return ResponseEntity.ok(product.getProductName() + " is updated successfully");
    }

    @DeleteMapping("/product/{productName}")
    ResponseEntity<?> deleteProduct(@PathVariable String productName) {
        if (StringUtils.isNullOrEmpty(productName)) {
            log.error("Product name cannot be empty or null");
            return ResponseEntity.badRequest().body("Product name cannot be empty");
        }

        productService.deleteProduct(productName);
        return ResponseEntity.ok("Product " + productName + " deleted successfully");
    }
}
