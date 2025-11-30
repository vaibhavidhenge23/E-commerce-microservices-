package com.OrderService.service;


import com.OrderService.Entity.Order;

import java.util.List;

public interface OrderService  {
    Order placeOrder(Order order);

    Order getOrder(Long id);

    List<Order> getAllOrders();
}
