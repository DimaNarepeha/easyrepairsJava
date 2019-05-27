package com.softserve.demo.service.impl;

import com.softserve.demo.model.Offer;
import com.softserve.demo.model.Service;
import com.softserve.demo.repository.ServicesRepository;
import com.softserve.demo.service.ServiceFromProviders;
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
    public Service updateService(Service service) {
        Service serviceFromDB = servicesRepository.getOne(service.getId());
        if (serviceFromDB == null) {
            return null;
        }
        return servicesRepository.save(service);
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
    public List<Service> getAllByOffer(Offer offer) {
        return servicesRepository.getAllByOffers(offer);
    }
}
