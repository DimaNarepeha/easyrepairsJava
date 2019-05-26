package com.softserve.demo.controller;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("register")
@Slf4j
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(final RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("verify/{activationCode}")
    public String verifyUserEmail(@PathVariable String activationCode) {
        if (registerService.verifyUser(activationCode)) {
            return "Successfully verified!";
        } else {
            return "Failed to verify! Maybe your code has expired or it is already used :(";
        }
    }

    @PostMapping("customer")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createCustomer(@RequestBody final CustomerDTO customerDTO) {
        return registerService.createCustomer(customerDTO);
    }

    @PostMapping("provider")
    @ResponseStatus(HttpStatus.CREATED)
    public ProviderDTO createProvider(@RequestBody final ProviderDTO providerDTO) {
        return registerService.createProvider(providerDTO);
    }
}
