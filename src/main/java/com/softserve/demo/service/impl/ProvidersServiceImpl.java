package com.softserve.demo.service.impl;

import com.softserve.demo.model.Provider;
import com.softserve.demo.repository.ProviderRepository;
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
    private ProviderRepository providerRepository;

    @Override
    public Provider findById(Integer id) {
        return providerRepository.findById(id).get();
    }

    @Override
    public List<Provider> findAll() {
        return providerRepository.findAll();
    }

    @Override
    public Provider save(Provider providers) {
        return providerRepository.save(providers);
    }

    @Override
    public Provider update(Integer id, Provider providers) {
        Provider newProviders = providerRepository.findById(id).get();
        newProviders.setName(providers.getName());
        return newProviders;
    }

    @Override
    public void delete(Integer id) {
        providerRepository.delete(providerRepository.findById(id).get());
    }

    @Override
    public void addImageToCustomer(Integer id, String fileName) {
        Provider providers=
                providerRepository.findById(id).get();

        providers.setImage(fileName);
        providerRepository.save(providers);
    }

    @Override
    public Page<Provider> getServiceProvidersByPage(int page) {
        Page<Provider> serviceProviders =
                providerRepository.findAll(new PageRequest(page,4));
        return serviceProviders;
    }
}
