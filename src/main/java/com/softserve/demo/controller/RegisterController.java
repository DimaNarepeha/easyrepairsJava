package com.softserve.demo.controller;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.service.RegisterService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("register")
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(final RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("customer")
    public String createCustomer(@RequestBody final CustomerDTO customerDTO) {
        return registerService.createCustomer(customerDTO);
    }

    @PostMapping("provider")
    public String createProvider(@RequestBody final ProviderDTO providerDTO) {
        return registerService.createProvider(providerDTO);
    }
}
