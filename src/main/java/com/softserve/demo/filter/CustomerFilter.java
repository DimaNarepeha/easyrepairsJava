package com.softserve.demo.filter;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.dto.ProviderInfoDTO;
import com.softserve.demo.filter.specification.CustomerSpecification;
import com.softserve.demo.filter.specification.ProviderSpecification;
import com.softserve.demo.model.Customer;
import com.softserve.demo.model.CustomerStatus;
import com.softserve.demo.model.ProviderStatus;
import com.softserve.demo.service.CustomerService;
import com.softserve.demo.util.mappers.CustomerMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerFilter {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;


    public CustomerFilter(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
        this.customerService = customerService;
    }

    public Page<CustomerDTO> firstNameLike(int page, int numberOfProvidersOnPage, String searchName, CustomerStatus status) {
        Page<Customer> entityPage = customerService.findAll(Specification.where(CustomerSpecification.isStatus(status))
                        .and(CustomerSpecification.likeFirstName(searchName)),
                page, numberOfProvidersOnPage, "name");
        List<CustomerDTO> customerDTO = customerMapper.map(entityPage.getContent());
        return new PageImpl<CustomerDTO>(customerDTO, PageRequest.of(page, numberOfProvidersOnPage), entityPage.getTotalElements());
    }


    public Page<CustomerDTO> lastNameLike(int page, int numberOfProvidersOnPage, String searchName, CustomerStatus status) {
        Page<Customer> entityPage = customerService.findAll(Specification.where(CustomerSpecification.isStatus(status))
                        .and(CustomerSpecification.likeLastName(searchName)),
                page, numberOfProvidersOnPage, "name");
        List<CustomerDTO> customerDTO = customerMapper.map(entityPage.getContent());
        return new PageImpl<CustomerDTO>(customerDTO, PageRequest.of(page, numberOfProvidersOnPage), entityPage.getTotalElements());
    }
}
