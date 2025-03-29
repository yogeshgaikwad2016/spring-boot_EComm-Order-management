package com.project.ecommerce.order_management.service;

import com.project.ecommerce.order_management.model.Product;
import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProduct(String productName);

    void updateProduct(Product product);

    Product saveProduct(Product product);

    void deleteProduct(String productName);
}
