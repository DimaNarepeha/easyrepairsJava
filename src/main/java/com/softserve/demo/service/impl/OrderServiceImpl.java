package com.softserve.demo.service.impl;

import com.softserve.demo.model.Location;
import com.softserve.demo.model.Order;
import com.softserve.demo.repository.LocationRepository;
import com.softserve.demo.repository.OrderRepository;
import com.softserve.demo.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        Location locationFromDB = locationRepository.findLocationByCityAndCountry(
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
    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }
}
