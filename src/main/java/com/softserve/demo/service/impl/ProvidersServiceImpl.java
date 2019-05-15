package com.softserve.demo.service.impl;

import com.softserve.demo.model.ServiceProvider;
import com.softserve.demo.model.User;
import com.softserve.demo.repository.ProvidersRepository;
import com.softserve.demo.repository.UserRepository;
import com.softserve.demo.service.ProvidersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Illia Chenchak
 */

@Service
@Transactional
public class ProvidersServiceImpl implements ProvidersService {

    private final ProvidersRepository providersRepository;

    private final UserRepository userRepository;

    @Autowired
    public ProvidersServiceImpl(ProvidersRepository providersRepository, UserRepository userRepository) {
        this.providersRepository = providersRepository;
        this.userRepository = userRepository;
    }

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
        providers.setUser(userRepository.findById(1));
        Date date = new Date();
        providers.setLastUpdate((java.sql.Date) date);
        return providersRepository.save(providers);
    }

    @Override
    public ServiceProvider update(Integer id, ServiceProvider providers) {
        ServiceProvider newProviders = providersRepository.findById(id).get();
        Date date = new Date();
        providers.setLastUpdate((java.sql.Date) date);
        newProviders.setName(providers.getName());
        return newProviders;
    }

    @Override
    public void delete(Integer id) {
        providersRepository.delete(providersRepository.findById(id).get());
    }

    @Override
    public void addImageToProviderds(Integer id, String fileName) {
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
