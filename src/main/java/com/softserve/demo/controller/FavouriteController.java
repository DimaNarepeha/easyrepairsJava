package com.softserve.demo.controller;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("favourite")
public class FavouriteController {

    private final CustomerService customerService;

    public FavouriteController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("findAll/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CustomerDTO findAll(@RequestParam int customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PutMapping("addToFavourite/{customerId}/provider/{providerId}")
    public ResponseEntity<HttpStatus> addToFavourite(@PathVariable("customerId") Integer customerId,
                                            @PathVariable("providerId") Integer providerId) {
        customerService.addToFavourite(customerId, providerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("removeFromFavourite/{customerId}/provider/{providerId}")
    public ResponseEntity<HttpStatus> removeFromFavourite(@PathVariable("customerId") Integer customerId,
                                                     @PathVariable("providerId") Integer providerId) {
        customerService.removeFromFavourite(customerId, providerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

