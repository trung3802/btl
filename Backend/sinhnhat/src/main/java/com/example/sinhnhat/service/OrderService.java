package com.example.sinhnhat.service;

import java.util.List;

import com.example.sinhnhat.entity.Order;
import com.example.sinhnhat.model.request.CreateOrderRequest;

public interface OrderService {
    
    void placeOrder(CreateOrderRequest request);

    List<Order> getList();
    
    List<Order> getOrderByUser(String username);
}
