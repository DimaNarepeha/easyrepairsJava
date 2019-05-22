package com.softserve.demo.service;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.dto.ProviderDTO;

public interface RegisterService {
    CustomerDTO createCustomer(CustomerDTO customerDTO);

    ProviderDTO createProvider(ProviderDTO providerDTO);
}
