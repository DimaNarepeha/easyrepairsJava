package com.softserve.demo.service.Impl;

import com.softserve.demo.model.ServiceFromProviders;
import com.softserve.demo.repository.ServiceRepository;
import com.softserve.demo.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    ServiceRepository serviceRepository;


    @Override
    public void createService(ServiceFromProviders serviceFromProviders) {

    }

    @Override
    public ServiceFromProviders updateService(Long id, ServiceFromProviders serviceFromProviders) {
        return null;
    }

    @Override
    public List<ServiceFromProviders> getAllServices() {
        return null;
    }

    @Override
    public ServiceFromProviders deleteService(Long id) {
        return null;
    }

    @Override
    public ServiceFromProviders getServiceById(Long id) {
        return null;
    }

    @Override
    public Page<ServiceFromProviders> getServicesByPage(int page) {
        return null;
    }
}
