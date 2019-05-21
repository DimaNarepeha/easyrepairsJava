package com.softserve.demo.service.impl;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.model.Customer;
import com.softserve.demo.model.Provider;
import com.softserve.demo.model.User;
import com.softserve.demo.repository.CustomerRepository;
import com.softserve.demo.repository.ProviderRepository;
import com.softserve.demo.repository.UserRepository;
import com.softserve.demo.service.RegisterService;
import com.softserve.demo.util.CustomerMapper;
import com.softserve.demo.util.ProviderMapper;
import com.softserve.demo.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final ProviderRepository providerRepository;
    private final CustomerMapper customerMapper;
    private final ProviderMapper providerMapper;
    private static final String USERNAME_EXISTS = "This username already exist";
    private static final String EMAIL_EXISTS = "This email already used";
    private static final String OK = "Everything is fine";

    public RegisterServiceImpl(
            final CustomerMapper customerMapper,
            final CustomerRepository customerRepository,
            final UserRepository userRepository,
            final ProviderRepository providerRepository, ProviderMapper providerMapper) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.providerRepository = providerRepository;
        this.customerMapper = customerMapper;
        this.providerMapper = providerMapper;
    }

    @Override
    @Transactional
    public String createCustomer(final CustomerDTO customerDTO) {
        User user = UserMapper.INSTANCE.UserDTOToUser(customerDTO.getUserDTO());
        if (userRepository.existsByUsername(user.getUsername())) {
            return USERNAME_EXISTS;
        }
        if (customerRepository.existsByEmail(customerDTO.getEmail())) {
            return EMAIL_EXISTS;
        }
        userRepository.save(user);
        user = userRepository.findByUsername(user.getUsername());
        Customer customer = customerMapper.CustomerDTOToCustomer(customerDTO);
        customer.setUser(user);
        customerRepository.save(customer);
        return OK;
    }

    @Override
    @Transactional
    public String createProvider(final ProviderDTO providerDTO) {
        User user = UserMapper.INSTANCE.UserDTOToUser(providerDTO.getUserDTO());
        if (userRepository.existsByUsername(user.getUsername())) {
            return USERNAME_EXISTS;
        }
        if (providerRepository.existsByEmail(providerDTO.getEmail())) {
            return EMAIL_EXISTS;
        }
        userRepository.save(user);
        user = userRepository.findByUsername(user.getUsername());
        Provider provider = providerMapper.ProviderDTOToProvider(providerDTO);
        provider.setUser(user);
        providerRepository.save(provider);
        return OK;
    }
}
