package com.softserve.demo.service;


import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.model.Customer;
import com.softserve.demo.model.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {


    CustomerDTO updateCustomer( CustomerDTO customer);

    List<CustomerDTO> getAllCustomers();

    void deleteCustomer(Integer id);

    CustomerDTO getCustomerById(Integer id);

    Page<CustomerDTO> getCustomersByPage(Pageable pageable);

    void addImageToCustomer(Integer id, String fileName);

    CustomerDTO findCustomerByUserId (Integer id);
}
