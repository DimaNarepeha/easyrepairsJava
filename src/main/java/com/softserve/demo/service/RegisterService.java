package com.softserve.demo.service;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.dto.ProviderDTO;

public interface RegisterService {
    String createCustomer(CustomerDTO customerDTO);

    String createProvider(ProviderDTO providerDTO);
}
