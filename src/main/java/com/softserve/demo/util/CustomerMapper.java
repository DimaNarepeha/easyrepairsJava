package com.softserve.demo.util;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "image", source = "image"),
            @Mapping(target = "updated", source = "updated"),
            @Mapping(target = "userDTO", source = "user")
    })
    CustomerDTO CustomerToCustomerDTO(Customer customer);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "image", source = "image"),
            @Mapping(target = "updated", source = "updated"),
            @Mapping(target = "user", source = "userDTO")
    })
    Customer CustomerDTOToCustomer(CustomerDTO customerDTO);
}
