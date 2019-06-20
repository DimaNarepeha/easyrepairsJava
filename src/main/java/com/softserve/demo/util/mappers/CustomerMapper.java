package com.softserve.demo.util.mappers;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.model.Customer;
import com.softserve.demo.model.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mappings({
            @Mapping(target = "userDTO", source = "user"),
            @Mapping(target = "image", source = "customer.user.image"),
            @Mapping(target = "email", source = "customer.user.email"),
            @Mapping(target = "favourites", source = "customer.favourites")

    })
    CustomerDTO customerToCustomerDTO(Customer customer);

    @Mappings({
           @Mapping(target = "user", source = "userDTO"),
            @Mapping(target = "user.image", source = "image"),
            @Mapping(target = "user.email", source = "customerDTO.email")
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

    @IterableMapping(qualifiedByName = "toDto")
    List<CustomerDTO> map (List<Customer> providers);
}
