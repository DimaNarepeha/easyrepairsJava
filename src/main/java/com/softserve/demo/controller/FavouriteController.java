package com.softserve.demo.controller;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.service.CustomerService;
import com.softserve.demo.service.CustomerService;
import com.softserve.demo.service.ProvidersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("favourite")
public class FavouriteController {

    private final CustomerService customerService;
    private final ProvidersService providersService;

    public FavouriteController( CustomerService customerService, ProvidersService providersService) {
        this.customerService = customerService;
        this.providersService = providersService;
    }

    @GetMapping("findAll/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CustomerDTO findAll(@RequestParam int customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PutMapping("addToFavourite/{customerId}/provider/{providerId}")
    public ResponseEntity<?> addToFavourite(@PathVariable("customerId") Integer customerId,
                                         @PathVariable("providerId") Integer providerId) {
        final boolean[] isFavourite = new boolean[1];
        customerService.getCustomerById(customerId).getFavourite().forEach(
                providerDTO -> {
                    if (providerDTO.getId().intValue() == providerId) {
                        isFavourite[0] = true;
                    }
                });
        if (isFavourite[0]) {
            customerService.removeById(customerId, providerId);
        } else {
            customerService.addFavourite(customerId, providersService.findById(providerId));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

