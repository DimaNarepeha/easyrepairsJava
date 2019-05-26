package com.softserve.demo.util;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.model.Customer;
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
}
