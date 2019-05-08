package com.softserve.demo.service;



import com.softserve.demo.model.CustomerEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {
    void createCustomer(CustomerEntity customer);
    CustomerEntity updateCustomer(Long id, CustomerEntity customer);
    List<CustomerEntity> getAllCustomers();
    CustomerEntity deleteCustomer(Long id);
    CustomerEntity getCustomerById(Long id);
   Page<CustomerEntity> getCustomersByPage(int page);
   void addImageToCustomer(Long id, String fileName);
}
