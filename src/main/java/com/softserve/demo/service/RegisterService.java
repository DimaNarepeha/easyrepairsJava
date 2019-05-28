package com.softserve.demo.service;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.model.User;

public interface RegisterService {
    CustomerDTO createCustomer(CustomerDTO customerDTO);

    ProviderDTO createProvider(ProviderDTO providerDTO);

    User sendVerificationCode(User user);

    boolean verifyUser(String activationCode);
}
