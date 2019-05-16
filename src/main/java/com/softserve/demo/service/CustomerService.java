package com.softserve.demo.service;


import com.softserve.demo.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    void createCustomer(Customer customer);
    Customer updateCustomer(Integer id, Customer customer);
    List<Customer> getAllCustomers();
    Customer deleteCustomer(Integer id);
    Customer getCustomerById(Integer id);
    Page<Customer> getCustomersByPage(Pageable pageable);
   void addImageToCustomer(Integer id, String fileName);
}
