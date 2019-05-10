package com.softserve.demo.controller;

import com.softserve.demo.model.Service;
import com.softserve.demo.service.ServiceFromProviders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("Services")
public class ServiceController {

    @Autowired
    ServiceFromProviders serviceFromProviders;

    @PostMapping
    public ResponseEntity<?> createService(@RequestBody Service service) {
        this.serviceFromProviders.createService(service);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllServices() {
        return new ResponseEntity<>(serviceFromProviders.getAllServices(), HttpStatus.OK);
    }

    @GetMapping("{serviceId}")
    public ResponseEntity<?> getServiceById(@PathVariable("serviceId") Integer id) {
        return new ResponseEntity<>(serviceFromProviders.getServiceById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateService(@PathVariable("id") Integer id, @RequestBody Service service) {
        Service serviceUpdated = serviceFromProviders.updateService(id, service);

        if (serviceUpdated == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(serviceUpdated, HttpStatus.OK);
    }

    @DeleteMapping("{serviceId}")
    public ResponseEntity<?> deleteServiceFromProvidersById(@PathVariable("serviceId") Integer id) {
        serviceFromProviders.deleteService(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("list")
    public Page<Service> getServiceFromProvidersByPage(@RequestParam(defaultValue = "0") int page) {
        return serviceFromProviders.getServicesByPage(page);
    }
}
