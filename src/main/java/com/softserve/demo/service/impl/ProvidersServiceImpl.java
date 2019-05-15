package com.softserve.demo.service.impl;


import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.model.Provider;
import com.softserve.demo.repository.ProviderRepository;
import com.softserve.demo.repository.UserRepository;
import com.softserve.demo.service.ProvidersService;
import com.softserve.demo.util.ProviderMapper;
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
    public ProviderDTO save(ProviderDTO providerDTO) {
        Provider provider = ProviderMapper.INSTANCE.ProviderDTOToProvider(providerDTO);
        provider.setUser(userRepository.findById(1));
        Date uDate = new java.util.Date();
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        provider.setLastUpdate(sDate);
        provider.setImage("nophoto.png");
        providerRepository.save(provider);
        ProviderDTO newProviderDTO = ProviderMapper.INSTANCE.ProviderToProviderDTO(provider);
        return newProviderDTO;
    }

    @Override
    public ProviderDTO update(Integer id, ProviderDTO providerDTO) {
        Provider provider = ProviderMapper.INSTANCE.ProviderDTOToProvider(providerDTO);
        Provider newProvider = providerRepository.findById(id).get();
        Date uDate = new java.util.Date();
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        newProvider.setLastUpdate(sDate);
        newProvider.setName(provider.getName());
        newProvider.setEmail(provider.getEmail());
        newProvider.setDescription(provider.getDescription());
        ProviderDTO newProviderDTO = ProviderMapper.INSTANCE.ProviderToProviderDTO(newProvider);
        return newProviderDTO;

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
                providerRepository.findAll(PageRequest.of(page,4));
        return serviceProviders;
    }
}
