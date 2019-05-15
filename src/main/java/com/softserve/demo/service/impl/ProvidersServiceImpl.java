package com.softserve.demo.service.impl;


import com.softserve.demo.model.Provider;
import com.softserve.demo.repository.ProviderRepository;
import com.softserve.demo.repository.UserRepository;
import com.softserve.demo.service.ProvidersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Illia Chenchak
 */

@Service
@Transactional
public class ProvidersServiceImpl implements ProvidersService {

    private final ProviderRepository providerRepository;

    private final UserRepository userRepository;

    @Autowired
    public ProvidersServiceImpl(ProviderRepository providerRepository, UserRepository userRepository) {
        this.providerRepository = providerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Provider findById(Integer id) {
        return providerRepository.findById(id).get();
    }

    @Override
    public List<Provider> findAll() {
        return providerRepository.findAll();
    }

    @Override
    public Provider save(Provider provider) {
        provider.setUser(userRepository.findById(1));
        Date date = new Date();
        provider.setLastUpdate((java.sql.Date) date);
        return providerRepository.save(provider);
    }

    @Override
    public Provider update(Integer id, Provider provider) {
        Provider newProvider = providerRepository.findById(id).get();
        Date date = new Date();
        newProvider.setLastUpdate((java.sql.Date) date);
        newProvider.setName(provider.getName());
        newProvider.setEmail(provider.getEmail());
        newProvider.setDescription(provider.getDescription());
        return newProvider;

    }


    @Override
    public void delete(Integer id) {
        providerRepository.delete(providerRepository.findById(id).get());
    }

    @Override
    public void addImageToProviderds(Integer id, String fileName) {
        Provider provider =
                providerRepository.findById(id).get();
        provider.setImage(fileName);
        providerRepository.save(provider);
    }

    @Override
    public Page<Provider> getServiceProvidersByPage(int page) {
        Page<Provider> serviceProviders =
                providerRepository.findAll(new PageRequest(page, 4));
        return serviceProviders;
    }
}
