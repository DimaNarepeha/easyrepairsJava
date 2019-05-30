package com.softserve.demo.controller;

import com.softserve.demo.dto.ServiceDTO;
import com.softserve.demo.service.ServiceFromProviders;
import com.softserve.demo.util.ServiceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("services")
@Slf4j
public class ServiceController {

    private final ServiceFromProviders serviceFromProviders;
    private final ServiceMapper serviceMapper;

    public ServiceController(ServiceFromProviders serviceFromProviders, ServiceMapper serviceMapper) {
        this.serviceFromProviders = serviceFromProviders;
        this.serviceMapper = serviceMapper;
    }

    @GetMapping
    public List<ServiceDTO> getAllServices() {
        return serviceMapper.servicesToServiceDTOs(serviceFromProviders.getAllServices());
    }
}
