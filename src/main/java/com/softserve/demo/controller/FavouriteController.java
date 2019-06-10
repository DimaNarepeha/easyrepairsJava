package com.softserve.demo.controller;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.filter.ProviderFilter;
import com.softserve.demo.service.CustomerService;
import com.softserve.demo.service.ProvidersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("favourite")
public class FavouriteController {

    private final ProviderFilter providerFilter;
    private final CustomerService customerService;
    private final ProvidersService providersService;

    public FavouriteController(ProviderFilter providerFilter, CustomerService customerService, ProvidersService providersService) {
        this.providerFilter = providerFilter;
        this.customerService = customerService;
        this.providersService = providersService;
    }

    @GetMapping("findAll/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CustomerDTO findAll(@RequestParam int customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PutMapping("addToFavourite/{customerId}/provider/{providerId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addToFavourite(@PathVariable("customerId") Integer customerId,
                                         @PathVariable("providerId") Integer providerId) {
        System.out.println("customerId " + customerId + " id " + providerId);
        final boolean[] isTru = new boolean[1];
        customerService.getCustomerById(customerId).getFavourite().forEach(
                providerDTO -> {
                    if (providerDTO.getId().intValue() == providerId) {
                        isTru[0] = true;
                    }
                });
        if (isTru[0]) {
            customerService.removeById(customerId, providerId);
        } else {
            customerService.addFavourite(customerId, providersService.findById(providerId));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

