package com.softserve.demo.service.impl;

import com.softserve.demo.exceptions.NotFoundException;
import com.softserve.demo.model.*;
import com.softserve.demo.repository.LocationRepository;
import com.softserve.demo.repository.OrderRepository;
import com.softserve.demo.service.EmailService;
import com.softserve.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private static final String CONTRACT_WAS_CREATED = "New contract was created";
    private static final String CONTRACT_WAS_UPDATED = "Contract was updated";
    private static final String CONTRACT_WAS_DELETED = "Contract was deleted";
    private static final String BY = " by %s %s";
    private static final String WITH = " with %s";
    private static final String TEXT_CREATE_CONTRACT = "Dear %s,\n" +
            "Congratulations! You have a new contract with %s. Please follow this link, to view the details, " +
            "edit, reject or sign it.\n" +
            "If you received this message by mistake, please ignore it. If you want to complain, " +
            "please contact Admin.\n" +
            "Thank you for using our services!\n" +
            "Your easyrepairs.com";
    private static final String TEXT_DELETE_CONTRACT = "Dear %s,\n" +
            "Your contract with %s has been deleted. If you want to restore it, please contact Admin.\n" +
            "Thank you for using our services!\n" +
            "Your easyrepairs.com";

    private final OrderRepository orderRepository;
    private final LocationRepository locationRepository;
    private final NotificationServiceImpl notificationService;
    private final EmailService emailService;

    public OrderServiceImpl(OrderRepository orderRepository, LocationRepository locationRepository,
                            NotificationServiceImpl notificationService, EmailService emailService) {
        this.orderRepository = orderRepository;
        this.locationRepository = locationRepository;
        this.notificationService = notificationService;
        this.emailService = emailService;
    }

    @Override
    public Order createOrder(Order order) {
        notifyAboutOrder(order, CONTRACT_WAS_CREATED);
        emailAboutOrder(order, CONTRACT_WAS_CREATED, TEXT_CREATE_CONTRACT);
        order.setLocation(saveLocation(order.getLocation()));
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

        notifyAboutOrder(order, CONTRACT_WAS_UPDATED);
        order.setLocation(saveLocation(order.getLocation()));
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Integer id) {
        log.debug(String.format("Delete order with id: [%d]", id));
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Order with id: [%d] not found", id)));

        notifyAboutOrder(order, CONTRACT_WAS_DELETED);
        emailAboutOrder(order, CONTRACT_WAS_DELETED, TEXT_DELETE_CONTRACT);
        orderRepository.deleteById(id);
    }

    private Location saveLocation(Location location) {
        Location locationFromDB = locationRepository.findLocationByCityAndCountryAndRegion(
                location.getCity(), location.getCountry(), location.getRegion());

        if (locationFromDB == null) {
            return locationRepository.save(location);
        }
        return locationFromDB;
    }

    private void notifyAboutOrder(Order order, String header) {
        Notification notification = new Notification();
        notification.setHeader(header);
        notification.setMessage(header + String.format(
                BY, order.getCustomer().getLastName(), order.getCustomer().getFirstName())
                + String.format(WITH, order.getProvider().getName()));
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
