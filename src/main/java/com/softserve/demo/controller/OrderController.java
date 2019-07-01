package com.softserve.demo.controller;

import com.softserve.demo.dto.OrderDTO;
import com.softserve.demo.dto.UserDTO;
import com.softserve.demo.service.OrderService;
import com.softserve.demo.util.mappers.OrderMapper;
import com.softserve.demo.util.mappers.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("orders")
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final UserMapper userMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper, UserMapper userMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.userMapper = userMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public OrderDTO createOrder(@RequestBody @Valid OrderDTO orderDTO) {
        return orderMapper.orderToOrderDTO(
                orderService.createOrder(orderMapper.orderDTOToOrder(orderDTO)));
    }

    @PutMapping
    @PreAuthorize("(hasRole('ROLE_PROVIDER') and principal.username == #orderDTO.providerDTO.userDTO.username) " +
            "or hasRole('ROLE_CUSTOMER') and principal.username == #orderDTO.customerDTO.userDTO.username")
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER')")
    public void deleteOrderById(@PathVariable("id") Integer id) {
        orderService.deleteOrder(id);
    }

    @GetMapping("/contract/{fileName}")
    @PreAuthorize("hasAnyRole('ROLE_PROVIDER', 'ROLE_CUSTOMER')")
    public Resource getOrderDocument(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        return orderService.getResponseResource(fileName, response);
    }

    @PutMapping("/email/contract/{id}")
    @PreAuthorize("hasAnyRole('ROLE_PROVIDER', 'ROLE_CUSTOMER')")
    public void sendOrderDocumentByEmail(@PathVariable("id") Integer id, @RequestBody @Valid UserDTO userDTO) {
        orderService.sendOrderByEmail(userMapper.userDTOToUser(userDTO), id);
    }
}
