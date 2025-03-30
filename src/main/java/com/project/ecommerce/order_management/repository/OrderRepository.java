package com.project.ecommerce.order_management.repository;

import com.project.ecommerce.order_management.model.SalesOrder;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<SalesOrder, String> {

    boolean existsBySalesOrderId(String orderId);
}
