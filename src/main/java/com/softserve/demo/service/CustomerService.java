package com.softserve.demo.service;


import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.model.Customer;
import com.softserve.demo.model.CustomerStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CustomerService {


    CustomerDTO updateCustomer( CustomerDTO customer);

    List<CustomerDTO> getAllCustomers();

    void deleteCustomer(Integer id);

    CustomerDTO getCustomerById(Integer id);

    Page<CustomerDTO> getCustomersByPage(Pageable pageable);

    void addImageToCustomer(Integer id, String fileName);

    CustomerDTO getCustomerByUserId (Integer id);

    Page<CustomerDTO> getCustomersByStatus(Pageable pageable, CustomerStatus status);

    CustomerDTO updateStatus(Integer id, String status);

    <T> Page<Customer> findAll(Specification<T> approved, int page, int numberOfProvidersOnPage, String sortBy);

     void addFavourite(Integer id, ProviderDTO providerDTO);

    void removeById(Integer customerId,Integer favouriteId);

}
