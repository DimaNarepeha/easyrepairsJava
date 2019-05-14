package com.softserve.demo.service.impl;

import com.softserve.demo.model.Customer;
import com.softserve.demo.repository.CustomerRepository;
import com.softserve.demo.repository.UserRepository;
import com.softserve.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
@Autowired
CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void createCustomer(Customer customer) {

        customer.setUser(userRepository.findById(1));
        java.util.Date uDate = new java.util.Date();
        System.out.println("Time in java.util.Date is : " + uDate);
        java.sql.Date sDate = convertUtilToSql(uDate);
        System.out.println("Time in java.sql.Date is : " + sDate);
        DateFormat df = new SimpleDateFormat("dd/MM/YYYY - hh:mm:ss");
        System.out.println("Using a dateFormat date is : " + df.format(uDate));
        customer.setUpdated(sDate);
        customerRepository.save(customer);
    }
    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    @Override
    public Customer updateCustomer(Integer id, Customer customer) {
        boolean exists = customerRepository.existsById(id);
        if (!exists) {
            return null;
        }

       Customer customerFromDB = customerRepository.findById(id).get();
        customerFromDB.setFirstName(customer.getFirstName());
        customerFromDB.setLastName(customer.getLastName());
        customerFromDB.setEmail(customer.getEmail());
        java.util.Date uDate = new java.util.Date();
        System.out.println("Time in java.util.Date is : " + uDate);
        java.sql.Date sDate = convertUtilToSql(uDate);
        System.out.println("Time in java.sql.Date is : " + sDate);
        DateFormat df = new SimpleDateFormat("dd/MM/YYYY - hh:mm:ss");
        System.out.println("Using a dateFormat date is : " + df.format(uDate));
        customerFromDB.setUpdated(sDate);
        customerRepository.save(customerFromDB);
        return customerFromDB;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    @Override
    public Customer deleteCustomer(Integer id) {
        if (!customerRepository.existsById(id)) {
            return null;
        }
        Customer customer =  customerRepository.findById(id).get();
       customerRepository.deleteById(id);
        return customer;
    }

    @Override
    public Customer getCustomerById(Integer id) {
        if(!customerRepository.existsById(id))return null;
        Customer customerEntity = customerRepository.findById(id).get();
        return customerEntity;
    }

    public Page<Customer> getCustomersByPage(int page) {
        Page<Customer> userEntities =
                customerRepository.findAll(new PageRequest(page,4));
        return userEntities;
    }

    @Override
    public void addImageToCustomer(Integer id, String fileName) {
        Customer customerEntity =
                customerRepository.findById(id).get();

       customerEntity.setImage(fileName);
       customerRepository.save(customerEntity);
    }
}
