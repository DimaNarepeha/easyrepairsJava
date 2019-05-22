package com.softserve.demo.controller;

import com.softserve.demo.dto.ServiceDTO;
import com.softserve.demo.model.Service;
import com.softserve.demo.service.ServiceFromProviders;
import com.softserve.demo.util.ServiceMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("services")
public class ServiceController {

    private final ServiceFromProviders serviceFromProviders;
    private final ServiceMapper serviceMapper;

    public ServiceController(ServiceFromProviders serviceFromProviders, ServiceMapper serviceMapper) {
        this.serviceFromProviders = serviceFromProviders;
        this.serviceMapper = serviceMapper;
    }

    @PostMapping("create")
    public ResponseEntity<?> createService(@RequestBody ServiceDTO serviceDTO) {
        serviceFromProviders.createService(serviceMapper.ServiceDTOToService(serviceDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("get-all")
    public ResponseEntity<?> getAllServices() {
        return new ResponseEntity<>(serviceMapper.ServicesToServiceDTOs(
                serviceFromProviders.getAllServices()), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteServiceById(@PathVariable("id") Integer id) {
        serviceFromProviders.deleteService(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
