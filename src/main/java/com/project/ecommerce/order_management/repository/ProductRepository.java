package com.project.ecommerce.order_management.repository;

import com.project.ecommerce.order_management.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductName(String productName);

    @Modifying
    @Transactional
    int deleteByProductName(String productName);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.price = :price, p.stock = :stock, p.description = :desc WHERE p.productName = :productName")
    int updateProduct(@Param("productName") String productName, @Param("price") Double price, @Param("stock") int stock, @Param("desc") String desc);
}
