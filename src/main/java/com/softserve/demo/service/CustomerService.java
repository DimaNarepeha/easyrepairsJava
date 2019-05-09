package com.softserve.demo.service;



import com.softserve.demo.model.CustomerEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {
    void createCustomer(CustomerEntity customer);
    CustomerEntity updateCustomer(Integer id, CustomerEntity customer);
    List<CustomerEntity> getAllCustomers();
    CustomerEntity deleteCustomer(Integer id);
    CustomerEntity getCustomerById(Integer id);
   Page<CustomerEntity> getCustomersByPage(int page);
   void addImageToCustomer(Integer id, String fileName);
}
