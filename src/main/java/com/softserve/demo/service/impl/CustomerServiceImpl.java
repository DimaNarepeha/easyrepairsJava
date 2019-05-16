package com.softserve.demo.service.impl;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.model.Customer;
import com.softserve.demo.repository.CustomerRepository;
import com.softserve.demo.repository.UserRepository;
import com.softserve.demo.service.CustomerService;
import com.softserve.demo.util.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createCustomer(CustomerDTO customerDTO) {
       Customer customer = CustomerMapper.INSTANCE.CustomerDTOToCustomer(customerDTO);
        customer.setUser(userRepository.findById(1));
        java.util.Date uDate = new java.util.Date();
        java.sql.Date sDate = convertUtilToSql(uDate);
        customer.setUpdated(sDate);
        customerRepository.save(customer);
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        return new java.sql.Date(uDate.getTime());
    }

    @Override
    public Customer updateCustomer(Integer id, CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.INSTANCE.CustomerDTOToCustomer(customerDTO);
        boolean exists = customerRepository.existsById(id);
        if (!exists) {
            return null;
        }
        Customer customerFromDB = customerRepository.findById(id).get();
        customerFromDB.setFirstName(customer.getFirstName());
        customerFromDB.setLastName(customer.getLastName());
        customerFromDB.setEmail(customer.getEmail());
        java.util.Date uDate = new java.util.Date();
        java.sql.Date sDate = convertUtilToSql(uDate);
        customerFromDB.setUpdated(sDate);
        customerRepository.save(customerFromDB);
        return customerFromDB;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(
                CustomerMapper.INSTANCE::CustomerToCustomerDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO deleteCustomer(Integer id) {
        if (!customerRepository.existsById(id)) {
            return null;
        }
        Customer customer = customerRepository.findById(id).get();
        customerRepository.deleteById(id);
        return CustomerMapper.INSTANCE.CustomerToCustomerDTO(customer);
    }

    @Override
    public CustomerDTO getCustomerById(Integer id) {
        if (!customerRepository.existsById(id)) {
            return null;
        }
        return CustomerMapper.INSTANCE.CustomerToCustomerDTO(customerRepository.findById(id).get());
    }

    public Page<CustomerDTO> getCustomersByPage(Pageable pageable) {
        return customerRepository.findAll(pageable)
                .map(CustomerMapper.INSTANCE::CustomerToCustomerDTO);
    }

    @Override
    public void addImageToCustomer(Integer id, String fileName) {
        Customer customerEntity =
                customerRepository.findById(id).get();
        customerEntity.setImage(fileName);
        customerRepository.save(customerEntity);
    }
}
