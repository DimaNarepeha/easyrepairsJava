package com.softserve.demo.service.impl;

import com.softserve.demo.model.ServiceProvider;
import com.softserve.demo.repository.ProvidersRepository;
import com.softserve.demo.service.ProvidersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Illia Chenchak
 */

@Service
@Transactional
public class ProvidersServiceImpl implements ProvidersService {

    @Autowired
    private ProvidersRepository providersRepository;

    @Override
    public ServiceProvider findById(Integer id) {
        return providersRepository.findById(id).get();
    }

    @Override
    public List<ServiceProvider> findAll() {
        return providersRepository.findAll();
    }

    @Override
    public ServiceProvider save(ServiceProvider providers) {
        return providersRepository.save(providers);
    }

    @Override
    public ServiceProvider update(Integer id, ServiceProvider providers) {
        ServiceProvider newProviders = providersRepository.findById(id).get();
        newProviders.setName(providers.getName());
        return newProviders;
    }

    @Override
    public void delete(Integer id) {
        providersRepository.delete(providersRepository.findById(id).get());
    }

    @Override
    public void addImageToCustomer(Integer id, String fileName) {
        ServiceProvider providers=
                providersRepository.findById(id).get();

        providers.setImage(fileName);
        providersRepository.save(providers);
    }

    @Override
    public Page<ServiceProvider> getServiceProvidersByPage(int page) {
        Page<ServiceProvider> serviceProviders =
                providersRepository.findAll(new PageRequest(page,4));
        return serviceProviders;
    }
}
