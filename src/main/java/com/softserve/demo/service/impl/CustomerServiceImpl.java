package com.softserve.demo.service.impl;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.exceptions.NotFoundException;
import com.softserve.demo.model.Customer;
import com.softserve.demo.repository.CustomerRepository;
import com.softserve.demo.repository.UserRepository;
import com.softserve.demo.service.CustomerService;
import com.softserve.demo.util.CustomerMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;


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
    public CustomerDTO updateCustomer(Integer id, CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.INSTANCE.CustomerDTOToCustomer(customerDTO);
        boolean exists = customerRepository.existsById(id);
        if (!exists) {
            return null;
        }
        java.util.Date uDate = new java.util.Date();
        java.sql.Date sDate = convertUtilToSql(uDate);
        customer.setUpdated(sDate);
        customerRepository.save(customer);
        return CustomerMapper.INSTANCE.CustomerToCustomerDTO(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(
                CustomerMapper.INSTANCE::CustomerToCustomerDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO deleteCustomer(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));

        customerRepository.deleteById(id);
        return CustomerMapper.INSTANCE.CustomerToCustomerDTO(customer);
    }

    @Override
    public CustomerDTO getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));
        return CustomerMapper.INSTANCE.CustomerToCustomerDTO(customer);
    }

    public Page<CustomerDTO> getCustomersByPage(Pageable pageable) {
        return customerRepository.findAll(pageable)
                .map(CustomerMapper.INSTANCE::CustomerToCustomerDTO);
    }

    @Override
    public void addImageToCustomer(Integer id, String fileName) {
        Customer customerEntity =
                customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));
        customerEntity.setImage(fileName);
        customerRepository.save(customerEntity);
    }
}
