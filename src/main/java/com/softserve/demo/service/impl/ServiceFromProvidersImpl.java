package com.softserve.demo.service.impl;

import com.softserve.demo.dto.ServiceDTO;
import com.softserve.demo.model.Provider;
import com.softserve.demo.model.Service;
import com.softserve.demo.repository.ProviderRepository;
import com.softserve.demo.repository.ServicesRepository;
import com.softserve.demo.service.ServiceFromProviders;
import com.softserve.demo.util.mappers.ServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceFromProvidersImpl implements ServiceFromProviders {

    private ServicesRepository servicesRepository;

    private final ServiceMapper serviceMapper;

    private final ProviderRepository providerRepository;

    public ServiceFromProvidersImpl(ServicesRepository servicesRepository, ServiceMapper serviceMapper, ProviderRepository providerRepository) {
        this.servicesRepository = servicesRepository;
        this.serviceMapper = serviceMapper;
        this.providerRepository = providerRepository;
    }

    @Override
    public List<Service> getAllServices() {
        return servicesRepository.findAll();
    }

    @Override
    public Service getServiceById(Integer id) {
        return servicesRepository.getOne(id);
    }

    @Override
    public ServiceDTO saveServiceFromProvider(Integer id, ServiceDTO serviceDTO) {
        Service service = servicesRepository.findByServiceName(serviceDTO.getServiceName());
        Provider provider = providerRepository.findById(id).get();
        provider.getServices().removeIf(oldService -> oldService.getServiceName().equals(service.getServiceName()));
        provider.getServices().add(service);
        providerRepository.save(provider);
        return serviceDTO;
    }

    @Override
    public List<ServiceDTO> findAllServicesNotPresentInProviders(Integer idProvider) {
        Provider provider = providerRepository.findById(idProvider).get();
        List<Service> notPresentService = servicesRepository.findAll();
//        for (Service s: provider.getServices()
//             ) {
//            notPresentService.removeIf(service -> service.getServiceName().equals(s.getServiceName()));
//        }

        provider.getServices().forEach(s ->
            notPresentService.removeIf(service -> service.getServiceName().equals(s.getServiceName()))
        );
        return notPresentService.stream()
                .map(serviceMapper::serviceToServiceDTO).collect(Collectors.toList());
    }
}
