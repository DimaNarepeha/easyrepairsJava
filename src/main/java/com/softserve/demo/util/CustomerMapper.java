package com.softserve.demo.util;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.model.Customer;
import com.softserve.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mappings({
            @Mapping(target = "userDTO", source = "user")
    })
    CustomerDTO customerToCustomerDTO(Customer customer);

    @Mappings({
            @Mapping(target = "user", source = "userDTO")
    })
    Customer customerDTOToCustomer(CustomerDTO customerDTO);

    @Mappings({
            @Mapping(target = "id", source = "userDTO.id"),
            @Mapping(target = "username", source = "userDTO.username"),
            @Mapping(target = "password", source = "userDTO.password"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "image", source = "image")
    })
    User customerDTOToUser(CustomerDTO customerDTO);
}
