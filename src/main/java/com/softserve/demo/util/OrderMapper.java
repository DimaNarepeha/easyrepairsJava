package com.softserve.demo.util;

import com.softserve.demo.dto.OrderDTO;
import com.softserve.demo.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "providerDTO", source = "provider")
    @Mapping(target = "customerDTO", source = "customer")
    @Mapping(target = "serviceDTOs", source = "services")
    @Mapping(target = "locationDTO", source = "location")
    OrderDTO orderToOrderDTO(Order order);

    @Mapping(target = "provider", source = "providerDTO")
    @Mapping(target = "customer", source = "customerDTO")
    @Mapping(target = "services", source = "serviceDTOs")
    @Mapping(target = "location", source = "locationDTO")
    Order orderDTOToOrder(OrderDTO orderDTO);

    List<OrderDTO> ordersToOrderDTOs(List<Order> orders);

    List<Order> orderDTOsToOrder(List<OrderDTO> orderDTOs);
}
