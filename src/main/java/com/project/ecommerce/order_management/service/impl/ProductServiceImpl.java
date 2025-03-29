package com.project.ecommerce.order_management.service.impl;

import com.project.ecommerce.order_management.exception.ProductNotFound;
import com.project.ecommerce.order_management.model.Product;
import com.project.ecommerce.order_management.repository.ProductRepository;
import com.project.ecommerce.order_management.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(String productName) {
        return productRepository.findByProductName(productName)
                .orElseThrow(() -> new ProductNotFound(productName + " not found, Please ask admin to create."));
    }

    @Override
    public void updateProduct(Product product) {
        getProduct(product.getProductName());
        int cn = productRepository.updateProduct(product.getProductName(), product.getPrice(), product.getStock(), product.getDescription());

        log.info("Total updated products are: {}", cn);
    }

    @Override
    public Product saveProduct(Product product) {
        Optional<Product> existingProduct = productRepository.findByProductName(product.getProductName());
        if (existingProduct.isPresent()) {
            updateProduct(product);
            return product;
        }

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(String productName) {
        getProduct(productName);
        int cn = productRepository.deleteByProductName(productName);
        log.info("Total deleted product are: {}", cn);
    }
}
