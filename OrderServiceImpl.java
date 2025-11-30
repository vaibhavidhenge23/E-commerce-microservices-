package com.OrderService.service.impl;

import com.OrderService.Entity.Order;
import com.OrderService.Repository.OrderRepository;
import com.OrderService.service.OrderService;
import com.OrderService.service.ProductClient;
import com.OrderService.service.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;
    
    @Override
    public Order placeOrder(Order order) {
        ProductResponse product = productClient.getProductById(order.getProductId());

        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        Long total = product.getPrice() * order.getQuantity();
        order.setTotalPrice(total);

        return orderRepository.save(order);
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(null);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
