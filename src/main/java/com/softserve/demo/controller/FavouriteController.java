package com.softserve.demo.controller;

import com.softserve.demo.dto.CustomerDTO;
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
        customerService.addOrRemoveFromFavourites(customerId, providerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

