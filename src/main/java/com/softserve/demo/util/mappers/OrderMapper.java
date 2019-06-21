package com.softserve.demo.util.mappers;

import com.softserve.demo.dto.OrderDTO;
import com.softserve.demo.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "providerDTO", source = "provider")
    @Mapping(target = "providerDTO.userDTO", source = "provider.user")
    @Mapping(target = "providerDTO.serviceDTOs", source = "provider.services")
    @Mapping(target = "customerDTO", source = "customer")
    @Mapping(target = "customerDTO.userDTO", source = "customer.user")
    @Mapping(target = "serviceDTOs", source = "services")
    @Mapping(target = "locationDTO", source = "location")
    OrderDTO orderToOrderDTO(Order order);

    @Mapping(target = "provider", source = "providerDTO")
    @Mapping(target = "provider.user", source = "providerDTO.userDTO")
    @Mapping(target = "provider.services", source = "providerDTO.serviceDTOs")
    @Mapping(target = "customer", source = "customerDTO")
    @Mapping(target = "customer.user", source = "customerDTO.userDTO")
    @Mapping(target = "services", source = "serviceDTOs")
    @Mapping(target = "location", source = "locationDTO")
    Order orderDTOToOrder(OrderDTO orderDTO);

    List<OrderDTO> ordersToOrderDTOs(List<Order> orders);

    List<Order> orderDTOsToOrder(List<OrderDTO> orderDTOs);
}
