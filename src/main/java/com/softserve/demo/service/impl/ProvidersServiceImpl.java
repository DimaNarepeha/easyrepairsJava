package com.softserve.demo.service.impl;

import com.softserve.demo.model.Providers;
import com.softserve.demo.repository.ProvidersRepository;
import com.softserve.demo.service.ProvidersService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Providers findById(Integer id) {
        return providersRepository.findById(id).get();
    }

    @Override
    public List<Providers> findAll() {
        return providersRepository.findAll();
    }

    @Override
    public Providers save(Providers providers) {
        return providersRepository.save(providers);
    }

    @Override
    public Providers update(Integer id, Providers providers) {
        Providers newProviders = providersRepository.findById(id).get();
        newProviders.setName(providers.getName());
        return newProviders;
    }

    @Override
    public void delete(Integer id) {
        providersRepository.delete(providersRepository.findById(id).get());
    }
}
