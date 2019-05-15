package com.softserve.demo.controller;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.dto.UserDTO;
import com.softserve.demo.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("register")
public class RegisterController {

    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }


    @PostMapping("customer")
    public String createCustomer(@RequestBody CustomerDTO customerDTO){
        registerService.createCustomer(customerDTO);
        return "this User was created";
    }
    @PostMapping("provider")
    public String createProvider(@RequestBody ProviderDTO providerDTO){
        registerService.createProvider(providerDTO);
        return "this User was created";
    }
}
