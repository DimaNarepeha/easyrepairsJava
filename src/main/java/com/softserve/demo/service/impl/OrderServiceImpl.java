package com.softserve.demo.service.impl;

import com.softserve.demo.exceptions.NotFoundException;
import com.softserve.demo.model.Location;
import com.softserve.demo.model.Order;
import com.softserve.demo.repository.LocationRepository;
import com.softserve.demo.repository.OrderRepository;
import com.softserve.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private LocationRepository locationRepository;

    public OrderServiceImpl(OrderRepository orderRepository, LocationRepository locationRepository) {
        this.orderRepository = orderRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public Order createOrder(Order order) {
        Location location = order.getLocation();
        Location locationFromDB = locationRepository.findLocationByCityAndCountryAndRegion(
                location.getCity(), location.getCountry(), location.getRegion());
        if (locationFromDB == null) {
            locationRepository.save(location);
            return orderRepository.save(order);
        }
        order.setLocation(locationFromDB);
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
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Integer id) {
        log.debug(String.format("Delete order with id: [%d]", id));
        orderRepository.deleteById(id);
    }
}
