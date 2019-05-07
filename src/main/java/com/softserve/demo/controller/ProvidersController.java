package com.softserve.demo.controller;

import com.softserve.demo.model.Providers;
import com.softserve.demo.service.ProvidersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Illia Chenchak
 */
@RestController
@RequestMapping("service-providers")
@CrossOrigin("*")
public class ProvidersController {

    @Autowired
    private ProvidersService providersService;

    @PostMapping("save")
    public ResponseEntity<Providers> saveServiceProvider(@RequestBody Providers serviceProviders) {
        return new ResponseEntity<>(providersService.save(serviceProviders), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Providers> updateServiceProviders(@PathVariable("id") Integer id, @RequestBody Providers serviceProviders) {
        return new ResponseEntity<>(providersService.update(id, serviceProviders), HttpStatus.OK);
    }

    @GetMapping("find-all")
    public ResponseEntity<List<Providers>> findAll() {
        return new ResponseEntity<>(providersService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Providers> deleteServiceProvidersResponse(@PathVariable("id") Integer id) {
        providersService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("find-by-id/{id}")
    public ResponseEntity<Providers> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(providersService.findById(id), HttpStatus.OK);
    }
}
