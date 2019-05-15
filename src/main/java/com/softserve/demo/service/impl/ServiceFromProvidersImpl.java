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

    private ServicesRepository servicesRepository;

    public ServiceFromProvidersImpl(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }


    @Override
    public void createService(Service service) {
        servicesRepository.save(service);
    }

    @Override
    public void updateService(Service service) {
        Service serviceFromDB;
        if ((serviceFromDB = servicesRepository.getOne(service.getId())) != null) {
            serviceFromDB.setServiceName(service.getServiceName());
            servicesRepository.save(serviceFromDB);
        }
    }

    @Override
    public List<Service> getAllServices() {
        return servicesRepository.findAll();
    }

    @Override
    public void deleteService(Integer id) {
        servicesRepository.deleteById(id);
    }

    @Override
    public Service getServiceById(Integer id) {
        return servicesRepository.getOne(id);
    }

    @Override
    public Page<Service> getServicesByPage(int page) {
        return servicesRepository.findAll(new PageRequest(page, 4));
    }
}
