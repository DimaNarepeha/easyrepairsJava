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

    @Autowired
    public RegisterServiceImpl(CustomerRepository customerRepository, UserRepository userRepository, ProviderRepository providerRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.providerRepository = providerRepository;
    }

    @Override
    @Transactional
    public String createCustomer(CustomerDTO customerDTO) {
        User user = UserMapper.INSTANCE.UserDTOToUser(customerDTO.getUserDTO());
        userRepository.save(user);
        user = userRepository.findByUsername(user.getUsername());
        Customer customer = CustomerMapper.INSTANCE.CustomerDTOToCustomer(customerDTO);
        customer.setUser(user);
        customerRepository.save(customer);
        return "everything is OK";
    }

    @Override
    public String createProvider(ProviderDTO providerDTO) {
        User user = UserMapper.INSTANCE.UserDTOToUser(providerDTO.getUserDTO());
        userRepository.save(user);
        user = userRepository.findByUsername(user.getUsername());
        Provider provider = ProviderMapper.INSTANCE.ProviderDTOToProvider(providerDTO);
        provider.setUser(user);
        providerRepository.save(provider);
        return "everything is OK";
    }
}
