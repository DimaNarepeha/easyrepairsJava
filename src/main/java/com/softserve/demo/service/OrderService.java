package com.softserve.demo.service;

import com.softserve.demo.model.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(Order order);

    List<Order> getAllOrders();

    Order updateOrder(Order order);

    void deleteOrder(Integer id);
}
