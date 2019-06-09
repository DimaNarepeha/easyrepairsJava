package com.softserve.demo.controller;

import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.filter.ProviderFilter;
import com.softserve.demo.service.CustomerService;
import com.softserve.demo.service.ProvidersService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("favourite")
public class FavouriteController {

    public final ProviderFilter providerFilter;
    public final CustomerService customerService;
    private final ProvidersService providersService;

    public FavouriteController(ProviderFilter providerFilter, CustomerService customerService, ProvidersService providersService) {
        this.providerFilter = providerFilter;
        this.customerService = customerService;
        this.providersService = providersService;
    }

    @GetMapping("findAll/{customerId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<ProviderDTO> findAll(@PathVariable("customerId") Integer customerId) {
        return customerService.getCustomerById(customerId).getFavourite();
    }

    @PostMapping("addToFavourite/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public void addToFavourite(@PathVariable("customerId") Integer customerId,
                                            @RequestParam Integer id) {
        customerService.addFavourite(customerId, providersService.findById(id));
    }
}

