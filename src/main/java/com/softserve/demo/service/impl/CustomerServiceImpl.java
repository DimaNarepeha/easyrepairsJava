package com.softserve.demo.service.impl;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.exceptions.NotFoundException;
import com.softserve.demo.model.*;
import com.softserve.demo.model.Customer;
import com.softserve.demo.repository.CustomerRepository;
import com.softserve.demo.repository.UserRepository;
import com.softserve.demo.service.CustomerService;
import com.softserve.demo.service.ProvidersService;
import com.softserve.demo.util.mappers.CustomerMapper;
import com.softserve.demo.util.mappers.ProviderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final ProviderMapper providerMapper;
    private final ProvidersService providersService;
    private boolean isFavourite;

    public CustomerServiceImpl(ProvidersService providersService, CustomerMapper customerMapper, CustomerRepository customerRepository, UserRepository userRepositor, ProviderMapper providerMapper) {
        this.providerMapper = providerMapper;
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.providersService = providersService;
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
                customerMapper::customerToCustomerDTO).collect(toList());
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
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Customer with id: [%d] not found", id)));
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
    public void addOrRemoveFromFavourites(Integer customerId, Integer providerId) {
        if (getCustomerById(customerId).getFavourites().isEmpty()) {
            addToFavourite(customerId, providerId);
        } else {
            List<?> newExistingProviders = getCustomerById(customerId).getFavourites().stream().map(ProviderDTO::getId).collect(toList());
            if (newExistingProviders.contains(providerId)) {
                removeById(customerId, providerId);
            } else {
                addToFavourite(customerId, providerId);
            }
        }
//        getCustomerById(customerId).getFavourites().forEach(
//                providerDTO -> {
//                    if (providerDTO.getId().intValue() == providerId) {
//                        isFavourite = true;
//                    }
//                });
//        if (isFavourite) {
//            removeById(customerId, providerId);
//        } else {
//
//            Customer customer = customerRepository.findCustomerById(customerId);
//            List<Provider> list = customer.getFavourites();
//            list.add(providerMapper.providerDTOToProvider(providersService.findById(providerId)));
//            customer.setFavourites(list);
//            customerRepository.save(customer);
//        }
    }

    private void addToFavourite(Integer customerId, Integer providerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        List<Provider> list = customer.getFavourites();
        list.add(providerMapper.providerDTOToProvider(providersService.findById(providerId)));
        customer.setFavourites(list);
        customerRepository.save(customer);
    }

    @Override
    public void removeById(Integer customerId, Integer favouriteId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        List<Provider> list = customer.getFavourites();
        list.removeIf(p -> p.getId() == favouriteId);
//        int numberOfFavouriteProvider = 0;
//        for (Provider provider : list) {
//            numberOfFavouriteProvider++;
//            if (provider.getId().equals(favouriteId)) {
//                break;
//            }
//        }
//        list.remove(numberOfFavouriteProvider-1);
        customer.setFavourites(list);
        customerRepository.save(customer);
    }
}
