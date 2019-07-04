package com.softserve.demo.service.impl;

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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Value("${contract.folder}")
    private String contractsFolder;

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
        log.info("Update order with id: [{}]", order.getId());
        if (!orderRepository.existsById(order.getId())) {
            throw new NotFoundException(String.format(Constant.ORDER_NOT_FOUND, order.getId()));
        }
        order.setLocation(locationService.saveLocationIfNotExist(order.getLocation()));
        if (isOrderApproved(order)) {
            contractMaker.createContract(order);
        }
        notifyAboutOrder(order, Constant.CONTRACT_WAS_UPDATED);
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Integer id) {
        log.info("Delete order with id: [{}]", id);
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format(Constant.ORDER_NOT_FOUND, id)));

        notifyAboutOrder(order, Constant.CONTRACT_WAS_DELETED);
        emailAboutOrder(order, Constant.CONTRACT_WAS_DELETED, Constant.TEXT_DELETE_CONTRACT);
        orderRepository.deleteById(id);
    }

    @Override
    public Resource getResponseResource(String fileName, HttpServletResponse response) {
        log.info("Get response resource with name: [{}]", fileName);
        response.setContentType("text/csv; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setHeader("filename", fileName);
        try {
            return getResource(fileName);
        } catch (MalformedURLException e) {
            throw new NotFoundException("File not found during downloading");
        }
    }

    @Override
    public void sendOrderByEmail(User user, Integer orderId) {
        log.info("Send order with id: [{}] by email to user: [{}]", orderId, user.getUsername());
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new NotFoundException(String.format(Constant.ORDER_NOT_FOUND, orderId)));
        File file;
        try {
            file = getResource(order.getContractName()).getFile();
        } catch (IOException e) {
            throw new NotFoundException("File not found during sending by email");
        }
        emailService.sendEmailWithFile(user.getEmail(),
                Constant.RECEIVED_CONTRACT, Constant.YOU_RECEIVED_CONTRACT_BY_EMAIL, file);
    }

    private Resource getResource(String fileName) throws MalformedURLException {
        Path fileStorageLocation = Paths.get(contractsFolder).toAbsolutePath().normalize();
        Path pathToFile = fileStorageLocation.resolve(fileName);
        return new UrlResource(pathToFile.toUri());
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

    private boolean isApproved(String value) {
        return value.equals("approved");
    }
}
