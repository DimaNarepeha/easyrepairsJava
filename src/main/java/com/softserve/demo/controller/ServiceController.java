package com.softserve.demo.controller;

import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.dto.ServiceDTO;
import com.softserve.demo.model.Provider;
import com.softserve.demo.service.ServiceFromProviders;
import com.softserve.demo.util.mappers.ServiceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/save/{providerId}")
    public ServiceDTO saveServiceForProvider (@PathVariable("providerId") Integer id, @RequestBody ServiceDTO serviceDTO ) {
        return serviceFromProviders.saveServiceFromProvider(id, serviceDTO);
    }

    @GetMapping("not-in-provider/{providerId}")
    public List<ServiceDTO> findAllServiceIsNotPresentInProvider (@PathVariable("providerId") Integer id) {
        return serviceFromProviders.findAllServicesNotPresentInProviders(id);
    }

    @DeleteMapping("delete/{providerId}/{serviceName}")
    public List<ServiceDTO> deleteByServiceName (@PathVariable("providerId") Integer id, @PathVariable("serviceName") String serviceName) {
        System.out.println("------------------------------------------");
        System.out.println(id + "service: " + serviceName);
        System.out.println("------------------------------------------");
        return serviceFromProviders.deleteByServiceNameInProvider(id,serviceName);
    }
}
