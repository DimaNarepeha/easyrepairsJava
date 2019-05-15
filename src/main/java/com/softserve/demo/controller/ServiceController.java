package com.softserve.demo.controller;

import com.softserve.demo.model.Service;
import com.softserve.demo.service.ServiceFromProviders;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("services")
public class ServiceController {

    private ServiceFromProviders serviceFromProviders;

    public ServiceController(ServiceFromProviders serviceFromProviders) {
        this.serviceFromProviders = serviceFromProviders;
    }

    @PostMapping
    public ResponseEntity<?> createService(@RequestBody Service service) {
        serviceFromProviders.createService(service);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllServices() {
        return new ResponseEntity<>(serviceFromProviders.getAllServices(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getServiceById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(serviceFromProviders.getServiceById(id), HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateService(@RequestBody Service service) {
        serviceFromProviders.updateService(service);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteServiceFromProvidersById(@PathVariable("id") Integer id) {
        serviceFromProviders.deleteService(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("list")
    public Page<Service> getServiceFromProvidersByPage(@RequestParam(defaultValue = "0") int page) {
        return serviceFromProviders.getServicesByPage(page);
    }
}
