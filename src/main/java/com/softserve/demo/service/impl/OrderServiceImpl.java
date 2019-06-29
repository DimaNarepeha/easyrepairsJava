package com.softserve.demo.service.impl;

import com.itextpdf.text.DocumentException;
import com.softserve.demo.exceptions.NotFoundException;
import com.softserve.demo.model.*;
import com.softserve.demo.repository.OrderRepository;
import com.softserve.demo.service.EmailService;
import com.softserve.demo.service.LocationService;
import com.softserve.demo.service.NotificationService;
import com.softserve.demo.service.OrderService;
import com.softserve.demo.util.Constant;
import com.softserve.demo.util.ContractMaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Slf4j
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Value("${contract.path}")
    private String pathToContracts;

    private final OrderRepository orderRepository;
    private final NotificationService notificationService;
    private final EmailService emailService;
    private final LocationService locationService;
    private final ContractMaker contractMaker;

    public OrderServiceImpl(OrderRepository orderRepository, NotificationServiceImpl notificationService,
                            EmailService emailService, LocationServiceImpl locationService,
                            ContractMaker contractMaker) {

        this.orderRepository = orderRepository;
        this.notificationService = notificationService;
        this.emailService = emailService;
        this.locationService = locationService;
        this.contractMaker = contractMaker;
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
        if (!orderRepository.existsById(order.getId())) {
            throw new NotFoundException(String.format(Constant.ORDER_NOT_FOUND, order.getId()));
        }
        order.setLocation(locationService.saveLocationIfNotExist(order.getLocation()));
        if (isOrderApproved(order)) {
            try {
                contractMaker.createContract(order);
            } catch (IOException | DocumentException e) {
                log.error(e.getMessage());
            }
        }
        notifyAboutOrder(order, Constant.CONTRACT_WAS_UPDATED);
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Integer id) {
        log.debug("Delete order with id: [{}]", id);
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format(Constant.ORDER_NOT_FOUND, id)));

        notifyAboutOrder(order, Constant.CONTRACT_WAS_DELETED);
        emailAboutOrder(order, Constant.CONTRACT_WAS_DELETED, Constant.TEXT_DELETE_CONTRACT);
        orderRepository.deleteById(id);
    }

    @Override
    public Resource getResource(String fileName, HttpServletResponse response) {
        log.info("Get resource name " + fileName);
        response.setContentType("text/csv; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setHeader("filename", fileName);
        Resource resource = null;
        try {
            resource = new UrlResource(new File(pathToContracts + fileName).toPath().toUri());
        } catch (MalformedURLException e) {
            log.error("getResource error " + e.getMessage());
        }
        return resource;
    }

    @Override
    public void sendOrderByEmail(User user, Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new NotFoundException(String.format(Constant.ORDER_NOT_FOUND, orderId)));
        emailService.sendEmailWithFile(user.getEmail(),
                Constant.RECEIVED_CONTRACT, Constant.YOU_RECEIVED_CONTRACT_BY_EMAIL,
                new File(pathToContracts + order.getContractName()));
    }

    private void notifyAboutOrder(Order order, String header) {
        Provider provider = order.getProvider();
        Customer customer = order.getCustomer();
        Notification notification = new Notification();
        notification.setHeader(header);
        notification.setMessage(header + String.format(
                Constant.BY, customer.getLastName(), customer.getFirstName())
                + String.format(Constant.WITH, provider.getName()));
        notificationService.notifyByUserId(provider.getUser().getId(), notification);
    }

    private void emailAboutOrder(Order order, String header, String message) {
        Provider provider = order.getProvider();
        Customer customer = order.getCustomer();
        emailService.sendEmailTo(provider.getUser().getEmail(),
                header, String.format(message, provider.getName(), customer.getFirstName()));
        emailService.sendEmailTo(customer.getUser().getEmail(),
                header, String.format(message, customer.getFirstName(), provider.getName()));
    }

    private boolean isOrderApproved(Order order) {
        if (order.getCustomerApproved() == null || order.getProviderApproved() == null) {
            return false;
        }
        return (isApproved(order.getCustomerApproved()) && isApproved(order.getProviderApproved()));
    }

    private boolean isApproved(String x) {
        return x.equals("approved");
    }
}
