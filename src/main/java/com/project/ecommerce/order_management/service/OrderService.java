package com.project.ecommerce.order_management.service;

import com.project.ecommerce.order_management.model.OrderRequest;
import com.project.ecommerce.order_management.model.SalesOrder;

import java.util.List;

public interface OrderService {

    SalesOrder placeSO(OrderRequest orderRequest);

    SalesOrder updateSO(SalesOrder salesOrder);

    void deleteSO(String salesOrderId);

    SalesOrder getSO(String orderId);

    List<SalesOrder> getAllSO();
}
