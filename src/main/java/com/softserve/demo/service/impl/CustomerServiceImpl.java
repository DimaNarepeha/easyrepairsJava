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

    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public void createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setUser(userRepository.findById(1));
        customerRepository.save(customer);
    }

    @Override
    public CustomerDTO updateCustomer(Integer id, CustomerDTO customerDTO) {
        Customer customerFromDatabase = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customerRepository.save(customer);
        return customerMapper.customerToCustomerDTO(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(
                customerMapper::customerToCustomerDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO deleteCustomer(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));

        customerRepository.deleteById(id);
        return customerMapper.customerToCustomerDTO(customer);
    }

    @Override
    public CustomerDTO getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));
        return customerMapper.customerToCustomerDTO(customer);
    }

    public Page<CustomerDTO> getCustomersByPage(Pageable pageable) {
        return customerRepository.findAll(pageable)
                .map(customerMapper::customerToCustomerDTO);
    }

    @Override
    public void addImageToCustomer(Integer id, String fileName) {
        Customer customerEntity =
                customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));
        customerEntity.setImage(fileName);
        customerRepository.save(customerEntity);
    }
}
