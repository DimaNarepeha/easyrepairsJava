package com.softserve.demo.controller;

import com.softserve.demo.dto.OrderDTO;
import com.softserve.demo.service.OrderService;
import com.softserve.demo.util.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("orders")
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO createOrder(@RequestBody @Valid OrderDTO orderDTO) {
        return orderMapper.orderToOrderDTO(
                orderService.createOrder(orderMapper.orderDTOToOrder(orderDTO)));
    }

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderMapper.ordersToOrderDTOs(orderService.getAllOrders());
    }

    @DeleteMapping("/{id}")
    public void deleteOrderById(@PathVariable("id") Integer id) {
        orderService.deleteOrder(id);
    }
}
