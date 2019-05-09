package com.softserve.demo.service;

import com.softserve.demo.model.ServiceFromProviders;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ServiceService {
    void createService(ServiceFromProviders serviceFromProviders);
    ServiceFromProviders updateService(Long id, ServiceFromProviders serviceFromProviders);
    List<ServiceFromProviders> getAllServices();
    ServiceFromProviders deleteService(Long id);
    ServiceFromProviders getServiceById(Long id);
    Page<ServiceFromProviders> getServicesByPage(int page);
}
