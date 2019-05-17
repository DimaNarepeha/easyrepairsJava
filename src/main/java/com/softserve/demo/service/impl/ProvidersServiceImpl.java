package com.softserve.demo.service.impl;

import com.softserve.demo.model.Provider;
import com.softserve.demo.repository.ProvidersRepository;
import com.softserve.demo.service.ProvidersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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
    public Provider findById(Integer id) {
        return providersRepository.findById(id).get();
    }

    @Override
    public List<Provider> findAll() {
        return providersRepository.findAll();
    }

    @Override
    public Provider save(Provider providers) {
        return providersRepository.save(providers);
    }

    @Override
    public Provider update(Integer id, Provider providers) {
        Provider newProviders = providersRepository.findById(id).get();
        newProviders.setName(providers.getName());
        return newProviders;
    }

    @Override
    public void delete(Integer id) {
        providersRepository.delete(providersRepository.findById(id).get());
    }

    @Override
    public void addImageToCustomer(Integer id, String fileName) {
        Provider providers=
                providersRepository.findById(id).get();

        providers.setImage(fileName);
        providersRepository.save(providers);
    }

    @Override
    public Page<Provider> getServiceProvidersByPage(int page) {
        Page<Provider> serviceProviders =
                providersRepository.findAll(new PageRequest(page,4));
        return serviceProviders;
    }

    @Override
    public <T> List<Provider> findAll(Specification<T> approved) {
        return providersRepository.findAll(approved);
    }
}
