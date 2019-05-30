package com.softserve.demo.service.impl;

import com.softserve.demo.model.Service;
import com.softserve.demo.repository.ServicesRepository;
import com.softserve.demo.service.ServiceFromProviders;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceFromProvidersImpl implements ServiceFromProviders {

    private ServicesRepository servicesRepository;

    public ServiceFromProvidersImpl(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    @Override
    public List<Service> getAllServices() {
        return servicesRepository.findAll();
    }
}
