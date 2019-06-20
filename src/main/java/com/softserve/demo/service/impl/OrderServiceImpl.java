package com.softserve.demo.service.impl;

import com.softserve.demo.exceptions.NotFoundException;
import com.softserve.demo.model.Customer;
import com.softserve.demo.model.Notification;
import com.softserve.demo.model.Order;
import com.softserve.demo.model.Provider;
import com.softserve.demo.repository.OrderRepository;
import com.softserve.demo.service.EmailService;
import com.softserve.demo.service.LocationService;
import com.softserve.demo.service.NotificationService;
import com.softserve.demo.service.OrderService;
import com.softserve.demo.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final NotificationService notificationService;
    private final EmailService emailService;
    private final LocationService locationService;

    public OrderServiceImpl(OrderRepository orderRepository, NotificationServiceImpl notificationService,
                            EmailService emailService, LocationServiceImpl locationService) {
        this.orderRepository = orderRepository;
        this.notificationService = notificationService;
        this.emailService = emailService;
        this.locationService = locationService;
    }

    @Override
    public Order createOrder(Order order) {
        notifyAboutOrder(order, Constant.CONTRACT_WAS_CREATED);
        emailAboutOrder(order, Constant.CONTRACT_WAS_CREATED, Constant.TEXT_CREATE_CONTRACT);
        order.setLocation(locationService.saveLocationIfNotExist(order.getLocation()));
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrder(Order order) {
        orderRepository.findById(order.getId()).orElseThrow(() ->
                new NotFoundException(String.format("Order with id: [%d] not found", order.getId())));

        notifyAboutOrder(order, Constant.CONTRACT_WAS_UPDATED);
        order.setLocation(locationService.saveLocationIfNotExist(order.getLocation()));
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Integer id) {
        log.debug("Delete order with id: [{}]", id);
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Order with id: [%d] not found", id)));

        notifyAboutOrder(order, Constant.CONTRACT_WAS_DELETED);
        emailAboutOrder(order, Constant.CONTRACT_WAS_DELETED, Constant.TEXT_DELETE_CONTRACT);
        orderRepository.deleteById(id);
    }

    private void notifyAboutOrder(Order order, String header) {
        Notification notification = new Notification();
        notification.setHeader(header);
        notification.setMessage(header + String.format(
                Constant.BY, order.getCustomer().getLastName(), order.getCustomer().getFirstName())
                + String.format(Constant.WITH, order.getProvider().getName()));
        notificationService.notifyByUserId(order.getProvider().getUser().getId(), notification);
    }

    private void emailAboutOrder(Order order, String header, String message) {
        Provider provider = order.getProvider();
        Customer customer = order.getCustomer();
        emailService.sendEmailTo(provider.getUser().getEmail(),
                header, String.format(message, provider.getName(), customer.getFirstName()));
        emailService.sendEmailTo(customer.getUser().getEmail(),
                header, String.format(message, customer.getFirstName(), provider.getName()));
    }
}
