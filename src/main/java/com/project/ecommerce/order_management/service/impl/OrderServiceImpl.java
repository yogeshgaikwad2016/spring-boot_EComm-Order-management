package com.project.ecommerce.order_management.service.impl;

import com.project.ecommerce.order_management.exception.InsufficientStock;
import com.project.ecommerce.order_management.exception.ProductNotFound;
import com.project.ecommerce.order_management.exception.SONotFound;
import com.project.ecommerce.order_management.model.*;
import com.project.ecommerce.order_management.repository.OrderRepository;
import com.project.ecommerce.order_management.repository.ProductRepository;
import com.project.ecommerce.order_management.service.OrderService;
import com.project.ecommerce.order_management.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductRepository productRepository;

    @Autowired
    OrderServiceImpl(OrderRepository orderRepository,
                     UserService userService,
                     ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public SalesOrder placeSO(OrderRequest orderRequest) {
        User user = userService.getUser(orderRequest.getEmailId());

        List<OrderItem> orderItems = new ArrayList<>();
        SalesOrder salesOrder = new SalesOrder();
        double totSOPrice = 0;

        salesOrder.setSalesOrderId(generateUniqueSoId());
        for (OrderItemRequest orderItemRequest : orderRequest.getItems()) {
            Product product = productRepository.findByProductName(orderItemRequest.getProductName())
                    .orElseThrow(() -> new ProductNotFound(orderItemRequest.getProductName() + " with name no product found."));

            int remStock = product.getStock() - orderItemRequest.getQuantity();
            if (remStock < 0) throw new InsufficientStock(product.getProductName() + " Does not have sufficient stock to place order.");
            product.setStock(remStock);
            productRepository.save(product);

            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(orderItemRequest.getQuantity());
            orderItem.setProduct(product);
            double orderPrice = (double) orderItem.getQuantity() * product.getPrice();
            orderItem.setTotalPrice(orderPrice);

            orderItems.add(orderItem);

            totSOPrice += orderPrice;
        }
        salesOrder.setOrderDate(new Date());
        salesOrder.setOrderItems(orderItems);
        salesOrder.setUser(user);
        salesOrder.setTotalSOPrice(totSOPrice);

        orderItems.forEach(item -> item.setSalesOrder(salesOrder));
        return orderRepository.save(salesOrder);
    }

    @Override
    public SalesOrder updateSO(SalesOrder salesOrder) {
        return orderRepository.save(salesOrder);
    }

    @Override
    public void deleteSO(String salesOrderId) {
        orderRepository.deleteById(salesOrderId);
    }

    @Override
    public SalesOrder getSO(String orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new SONotFound(orderId + " Does not exists, Please provide correct id."));
    }

    @Override
    public List<SalesOrder> getAllSO() {
        return orderRepository.findAll();
    }

    private String generateUniqueSoId() {
        Random random = new Random();
        String orderId;

        do {
            int randomNumber = 1000 + random.nextInt(9000); // Generate a 4-digit number
            orderId = "SO" + randomNumber;  // Example: SO7865
        } while (orderRepository.existsBySalesOrderId(orderId)); // Ensure uniqueness

        return orderId;
    }
}
