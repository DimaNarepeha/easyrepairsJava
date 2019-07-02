package com.softserve.demo.controller;

import com.softserve.demo.dto.OrderDTO;
import com.softserve.demo.service.OrderService;
import com.softserve.demo.util.mappers.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public OrderDTO createOrder(@RequestBody @Valid OrderDTO orderDTO) {
        return orderMapper.orderToOrderDTO(
                orderService.createOrder(orderMapper.orderDTOToOrder(orderDTO)));
    }

    @PutMapping
    @PreAuthorize("@accessPermission.orderUpdatePermission(#orderDTO, principal)")
    public OrderDTO updateOrder(@RequestBody @Valid OrderDTO orderDTO) {
        return orderMapper.orderToOrderDTO(
                orderService.updateOrder(orderMapper.orderDTOToOrder(orderDTO)));
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<OrderDTO> getAllOrders() {
        return orderMapper.ordersToOrderDTOs(orderService.getAllOrders());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or @accessPermission.canDeleteOrder(#id, principal)")
    public void deleteOrderById(@PathVariable("id") Integer id) {
        orderService.deleteOrder(id);
    }
}
