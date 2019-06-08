package com.softserve.demo.service.impl;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.exceptions.AlreadyExistException;
import com.softserve.demo.exceptions.VerificationFailedException;
import com.softserve.demo.model.*;
import com.softserve.demo.repository.CustomerRepository;
import com.softserve.demo.repository.LocationRepository;
import com.softserve.demo.repository.ProviderRepository;
import com.softserve.demo.repository.UserRepository;
import com.softserve.demo.service.EmailService;
import com.softserve.demo.service.PortfolioService;
import com.softserve.demo.service.RegisterService;
import com.softserve.demo.util.mappers.CustomerMapper;
import com.softserve.demo.util.mappers.ProviderMapper;
import com.softserve.demo.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final ProviderRepository providerRepository;
    private final CustomerMapper customerMapper;
    private final ProviderMapper providerMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final PortfolioService portfolioService;
    private final LocationRepository locationRepository;

    private static final String USERNAME_EXISTS = "This username already exist";
    private static final String EMAIL_EXISTS = "This email already used";



    public RegisterServiceImpl(
            final PasswordEncoder passwordEncoder,
            final CustomerMapper customerMapper,
            final ProviderMapper providerMapper,
            final CustomerRepository customerRepository,
            final UserRepository userRepository,
            final ProviderRepository providerRepository,
            final EmailService emailService,
            final PortfolioService portfolioService,
            final LocationRepository locationRepository) {
        this.passwordEncoder = passwordEncoder;
        this.providerMapper = providerMapper;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.providerRepository = providerRepository;
        this.customerMapper = customerMapper;
        this.emailService = emailService;
        this.portfolioService = portfolioService;
        this.locationRepository = locationRepository;
    }

    @Override
    @Transactional
    public CustomerDTO createCustomer(final CustomerDTO customerDTO) {
        User user = userRepository.save(createUser(customerMapper.customerDTOToUser(customerDTO), Role.CUSTOMER));
        log.info(user.getPassword());
        sendVerificationCode(user);

        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setUser(user);
        return customerMapper.customerToCustomerDTO(customerRepository.save(customer));
    }

    @Override
    @Transactional
    public ProviderDTO createProvider(final ProviderDTO providerDTO) {
        User user = userRepository.save(createUser(providerMapper.providerDTOToUser(providerDTO), Role.PROVIDER));
        sendVerificationCode(user);
        Provider provider = providerMapper.providerDTOToProvider(providerDTO);
        provider.setLocation(locationRepository.findById(Constant.DEFAULT_LOCATION).get());
        provider.setUser(user);
        provider = providerRepository.save(provider);
        portfolioService.createEmptyPortfolio(provider);
        return providerMapper.providerToProviderDTO(provider);
    }

    private User createUser(User user, Role role) {
        validateForEmailAndUsername(user.getEmail(), user.getUsername());
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.setImage(Constant.defaultPhoto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    private void validateForEmailAndUsername(String email, String username) {
        if (userRepository.existsByUsername(username)) {
            throw new AlreadyExistException(USERNAME_EXISTS);
        }
        if (userRepository.existsByEmail(email)) {
            throw new AlreadyExistException(EMAIL_EXISTS);
        }
    }

    /**
     * This method verify user by verification code
     * if user with this code is present in database.
     *
     * @param activationCode code by which user will be searched
     * @return true if user is present in database
     */
    @Override
    @Transactional
    public boolean verifyUser(final String activationCode) {
        User user = userRepository.findByActivationCode(activationCode)
                .orElseThrow(() -> new VerificationFailedException("Failed to verify! Your activation code is already used!"));
        user.setActivated(true);
        user.setActivationCode(null);
        return true;
    }

    /**
     * Sends verification link to the provided user.
     *
     * @param user to whom notification must be sent
     * @return user with set activation code
     */
    @Override
    @Transactional
    public User sendVerificationCode(final User user) {
        user.setActivationCode(UUID.randomUUID().toString());
        emailService.sendVerificationEmailTo(user);
        return user;
    }
}
