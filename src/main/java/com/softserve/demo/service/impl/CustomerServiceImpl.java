package com.softserve.demo.service.impl;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.exceptions.NotFoundException;
import com.softserve.demo.model.*;
import com.softserve.demo.repository.CustomerRepository;
import com.softserve.demo.repository.UserRepository;
import com.softserve.demo.service.CustomerService;
import com.softserve.demo.util.mappers.CustomerMapper;
import com.softserve.demo.util.mappers.ProviderMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final ProviderMapper providerMapper;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository, UserRepository userRepository, ProviderMapper providerMapper) {
        this.providerMapper = providerMapper;
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Customer customerFromDatabase = customerRepository.findById(customerDTO.getId()).orElseThrow(() -> new NotFoundException("Customer not found"));
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
    public void deleteCustomer(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerDTO getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));
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
                customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));
      customerEntity.getUser().setImage(fileName);
        customerRepository.save(customerEntity);
    }

    @Override
    public Customer getCustomerByOffer(Offer offer) {
        return customerRepository.findById(
                offer.getCustomer().getId()).orElseThrow(() -> new NotFoundException("Customer not found"));
    }

    @Override
    public CustomerDTO getCustomerByUserId(Integer id) {
        return customerMapper.customerToCustomerDTO(customerRepository.getCustomerByUserId(id));
    }

    @Override
    public Page<CustomerDTO> getCustomersByStatus(Pageable pageable, CustomerStatus status) {
        return customerRepository.findByStatus(status,pageable).map(customerMapper::customerToCustomerDTO);
    }

    @Override
    public CustomerDTO updateStatus(Integer id, String status) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));
        customer.setStatus(CustomerStatus.valueOf(status));
        customerRepository.save(customer);
        return customerMapper.customerToCustomerDTO(customer);
    }

    @Override
    public <T> Page<Customer> findAll(Specification<T> approved, int page, int numberOfProvidersOnPage, String sortBy) {
        return customerRepository.findAll(approved,PageRequest.of(page, numberOfProvidersOnPage, Sort.by(sortBy).descending()));
    }

    @Override
    public void addFavourite(Integer id, ProviderDTO providerDTO) {
        Customer customer = customerRepository.findCustomerById(id);
        List<Provider> list = customer.getFavourite();
        list.add(providerMapper.providerDTOToProvider(providerDTO));
        customer.setFavourite(list);
        customerRepository.save(customer);
    }

    @Override
    public void removeById(Integer customerId, Integer favouriteId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        List<Provider> list = customer.getFavourite();
//        List<Provider> newList = list.stream()
//                .filter(provider -> provider.getId() != favouriteId)
//                .collect(Collectors.toList());
        int count = 0;
        for (Provider provider : list) {
            count++;
            if (provider.getId().equals(favouriteId)) {
                break;
            }
        }

        list.remove(count-1);
        customer.setFavourite(list);
        customerRepository.save(customer);
    }
}
