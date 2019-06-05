package com.softserve.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
    private static final String SUCCESSFULLY_VERIFIED = "Successfully verified!";

    private ObjectMapper JsonMapper;

    public RegisterController(final RegisterService registerService, final ObjectMapper jsonMapper) {
        this.registerService = registerService;
        JsonMapper = jsonMapper;
    }

    @GetMapping("verify/{activationCode}")
    public ObjectNode verifyUserEmail(@PathVariable final String activationCode) {
        registerService.verifyUser(activationCode);
        ObjectNode jsonResponse = JsonMapper.createObjectNode();
        jsonResponse.put("message", SUCCESSFULLY_VERIFIED);
        return jsonResponse;
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
