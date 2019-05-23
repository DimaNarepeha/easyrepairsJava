package com.softserve.demo.service.impl;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.exceptions.AlreadyExistException;
import com.softserve.demo.model.Customer;
import com.softserve.demo.model.Provider;
import com.softserve.demo.model.Role;
import com.softserve.demo.model.User;
import com.softserve.demo.repository.CustomerRepository;
import com.softserve.demo.repository.ProviderRepository;
import com.softserve.demo.repository.UserRepository;
import com.softserve.demo.service.RegisterService;
import com.softserve.demo.util.CustomerMapper;
import com.softserve.demo.util.ProviderMapper;
import com.softserve.demo.util.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final ProviderRepository providerRepository;
    private final CustomerMapper customerMapper;
    private final UserMapper userMapper;
    private final ProviderMapper providerMapper;
    private final PasswordEncoder passwordEncoder;
    private static final String USERNAME_EXISTS = "This username already exist";
    private static final String EMAIL_EXISTS = "This email already used";

    public RegisterServiceImpl(
            final PasswordEncoder passwordEncoder,
            final UserMapper userMapper,
            final CustomerMapper customerMapper,
            final ProviderMapper providerMapper,
            final CustomerRepository customerRepository,
            final UserRepository userRepository,
            final ProviderRepository providerRepository) {
        this.passwordEncoder = passwordEncoder;
        this.providerMapper = providerMapper;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.providerRepository = providerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    @Transactional
    public CustomerDTO createCustomer(final CustomerDTO customerDTO) {
        User user = userMapper.UserDTOToUser(customerDTO.getUserDTO());
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new AlreadyExistException(USERNAME_EXISTS);
        }
        if (customerRepository.existsByEmail(customerDTO.getEmail()) && providerRepository.existsByEmail(customerDTO.getEmail())) {
            throw new AlreadyExistException(EMAIL_EXISTS);
        }
        Set<Role> roles = new HashSet<>();
        roles.add(Role.CUSTOMER);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(customerDTO.getUserDTO().getPassword()));
        log.info(user.getPassword());
        userRepository.save(user);
        user = userRepository.findByUsername(user.getUsername());
        Customer customer = customerMapper.CustomerDTOToCustomer(customerDTO);
        customer.setUser(user);
        log.info(customer.toString());
        customerRepository.save(customer);
        log.info(customer.toString());
        return customerMapper.CustomerToCustomerDTO(customerRepository.findByEmail(customer.getEmail()));
    }

    @Override
    @Transactional
    public ProviderDTO createProvider(final ProviderDTO providerDTO) {
        User user = userMapper.UserDTOToUser(providerDTO.getUserDTO());
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new AlreadyExistException(USERNAME_EXISTS);
        }
        if (customerRepository.existsByEmail(providerDTO.getEmail()) && providerRepository.existsByEmail(providerDTO.getEmail())) {
            throw new AlreadyExistException(EMAIL_EXISTS);
        }
        user.setPassword(passwordEncoder.encode(providerDTO.getUserDTO().getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(Role.PROVIDER);
        user.setRoles(roles);
        userRepository.save(user);
        user = userRepository.findByUsername(user.getUsername());
        Provider provider = providerMapper.ProviderDTOToProvider(providerDTO);
        provider.setUser(user);
        providerRepository.save(provider);
        return providerMapper.ProviderToProviderDTO(providerRepository.getByEmail(provider.getEmail()));
    }
}
