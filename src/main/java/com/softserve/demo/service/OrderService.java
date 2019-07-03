package com.softserve.demo.service;

import com.softserve.demo.model.Order;
import com.softserve.demo.model.User;
import org.springframework.core.io.Resource;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface OrderService {

    Order createOrder(Order order);

    List<Order> getAllOrders();

    Order updateOrder(Order order);

    void deleteOrder(Integer id);

    Resource getResource(String fileName, HttpServletResponse response);

    void sendOrderByEmail(User user, Integer orderId);
}
