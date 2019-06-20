package com.softserve.demo.service.impl;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.exceptions.NotFoundException;
import com.softserve.demo.model.Customer;
import com.softserve.demo.repository.CustomerRepository;
import com.softserve.demo.repository.UserRepository;
import com.softserve.demo.service.CustomerService;
import com.softserve.demo.util.mappers.CustomerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        if(!customerRepository.existsById(customerDTO.getId())){
            throw new NotFoundException(String.format("Customer with id: [%d] not found", customerDTO.getId()));
        }
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customerRepository.save(customer);
        log.debug(String.format("Update customer with id: [%d]", customerDTO.getId()));
        return customerMapper.customerToCustomerDTO(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(
                customerMapper::customerToCustomerDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteCustomer(Integer id) {
        if(!customerRepository.existsById(id)){
            throw new NotFoundException(String.format("Customer with id: [%d] not found",id));
        }
        customerRepository.deleteById(id);
        log.debug(String.format("Delete customer with id: [%d]", id));
    }

    @Override
    public CustomerDTO getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Customer with id: [%d] not found",id)));
        return customerMapper.customerToCustomerDTO(customer);
    }

    @Override
    public Page<CustomerDTO> getCustomersByPage(Pageable pageable) {
        return customerRepository.findAll(pageable)
                .map(customerMapper::customerToCustomerDTO);
    }

    @Override
    public void addImageToCustomer(Integer id, String fileName) {
        Customer customerEntity =
                customerRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Customer with id: [%d] not found",id)));
         customerEntity.getUser().setImage(fileName);
        customerRepository.save(customerEntity);
    }

    @Override
    public CustomerDTO findCustomerByUserId(Integer id) {
        return customerMapper.customerToCustomerDTO(customerRepository.findCustomerByUserId(id));
    }
}
