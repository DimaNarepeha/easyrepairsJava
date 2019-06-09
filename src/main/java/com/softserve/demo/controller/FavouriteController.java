package com.softserve.demo.controller;

import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.filter.ProviderFilter;
import com.softserve.demo.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("favourite")
public class FavouriteController {

    public final ProviderFilter providerFilter;
    public final CustomerService customerService;

    public FavouriteController(ProviderFilter providerFilter, CustomerService customerService) {
        this.providerFilter = providerFilter;
        this.customerService = customerService;
    }

//    @GetMapping("findAll/{customerId}")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public List<ProviderDTO> findAll(@PathVariable("customerId") Integer customerId) {
//        return customerService.getCustomerById(customerId).getFavourite();
//    }
//
//    @PostMapping("addToFavourite/{customerId}")
//    @ResponseStatus(HttpStatus.OK)
//    public List<ProviderDTO> addToFavourite(@PathVariable("customerId") Integer customerId,
//                                            @RequestBody ProviderDTO providerDTO) {
//        return new List<ProviderDTO>;
//    }

    @GetMapping("findAll/{customerId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<ProviderDTO> findAll(@PathVariable("customerId") Integer customerId) {
        return customerService.getCustomerById(customerId).getFavourite();
    }

//    @PostMapping("addToFavourite/{customerId}")
//    @ResponseStatus(HttpStatus.OK)
//    public List<ProviderDTO> addToFavourite(@PathVariable("customerId") Integer customerId,
//                                            @RequestBody ProviderDTO providerDTO) {
//        return new List<ProviderDTO>;
//    }


}

