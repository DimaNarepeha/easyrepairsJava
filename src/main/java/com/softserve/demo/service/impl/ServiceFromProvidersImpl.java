package com.softserve.demo.service.impl;

import com.softserve.demo.model.Service;
import com.softserve.demo.repository.ServicesRepository;
import com.softserve.demo.service.ServiceFromProviders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceFromProvidersImpl implements ServiceFromProviders {

    @Autowired
    ServicesRepository servicesRepository;


    @Override
    public void createService(Service service) {
        servicesRepository.save(service);
    }

    @Override
    public Service updateService(Integer id, Service service) {
        if (servicesRepository.existsById(id)) {
            Service serviceFromDB = servicesRepository.getOne(id);
            serviceFromDB.setServiceName(service.getServiceName());
            servicesRepository.save(serviceFromDB);
            return servicesRepository.getOne(id);
        }
        return null;
    }

    @Override
    public List<Service> getAllServices() {
        return servicesRepository.findAll();
    }

    @Override
    public Service deleteService(Integer id) {
        if (servicesRepository.existsById(id)) {
            Service serviceFromDB = servicesRepository.getOne(id);
            servicesRepository.deleteById(id);
            return serviceFromDB;
        }
        return null;
    }

    @Override
    public Service getServiceById(Integer id) {
        if (servicesRepository.existsById(id)) {
            return servicesRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Page<Service> getServicesByPage(int page) {
        return servicesRepository.findAll(new PageRequest(page, 4));
    }
}
