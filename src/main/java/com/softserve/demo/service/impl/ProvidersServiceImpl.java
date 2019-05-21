package com.softserve.demo.service.impl;


import com.softserve.demo.dto.LocationDTO;
import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.exceptions.NotFoundException;
import com.softserve.demo.model.Location;
import com.softserve.demo.model.Provider;
import com.softserve.demo.repository.LocationRepository;
import com.softserve.demo.repository.ProviderRepository;
import com.softserve.demo.repository.UserRepository;
import com.softserve.demo.service.ProvidersService;
import com.softserve.demo.util.LocationMapper;
import com.softserve.demo.util.Photo;
import com.softserve.demo.util.ProviderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Illia Chenchak
 */

@Service
@Transactional
public class ProvidersServiceImpl implements ProvidersService {

    private final ProviderRepository providerRepository;

    private final UserRepository userRepository;


    private final LocationRepository locationRepository;

    private final ProviderMapper providerMapper;

    private final LocationMapper locationMapper;


    public ProvidersServiceImpl(ProviderRepository providerRepository, UserRepository userRepository, LocationRepository locationRepository, ProviderMapper providerMapper, LocationMapper locationMapper) {
        this.providerRepository = providerRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.providerMapper = providerMapper;
        this.locationMapper = locationMapper;
    }

    @Override
    public ProviderDTO findById(Integer id) {
        Provider provider = providerRepository.findById(id).orElseThrow(() -> new NotFoundException("ServiceProvider not found"));
        return providerMapper.ProviderToProviderDTO(provider);
    }

    @Override
    public List<ProviderDTO> findAll() {
        return providerRepository.findAll().stream().map(
                providerMapper::ProviderToProviderDTO).collect(Collectors.toList());
    }

    @Override
    public ProviderDTO save(ProviderDTO providerDTO, LocationDTO locationDTO) {
        Provider provider = providerMapper.ProviderDTOToProvider(providerDTO);
        Location location1 = locationMapper.LocationDTOToLocation(locationDTO);
        provider.setUser(userRepository.findById(1));

        Location currentLoc = locationRepository.findLocationByCityAndCountry(location1.getCity(), location1.getCountry());
        if (currentLoc == null) {
            locationRepository.save(location1);
            provider.setLocation(location1);
        } else {
            provider.setLocation(currentLoc);
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        provider.setLastUpdate(localDateTime);
        provider.setImage(Photo.defaultPhoto);
        providerRepository.save(provider);
        ProviderDTO newProviderDTO = providerMapper.ProviderToProviderDTO(provider);
        return newProviderDTO;
    }

    @Override
    public ProviderDTO update(Integer id, ProviderDTO providerDTO, LocationDTO locationDTO) {
        Provider provider = providerMapper.ProviderDTOToProvider(providerDTO);
        Provider newProvider = providerRepository.findById(id).orElseThrow(() -> new NotFoundException("ServiceProvider not found"));
        Location location1 = locationMapper.LocationDTOToLocation(locationDTO);
        Location newLoc = locationRepository.findLocationByCityAndCountry(location1.getCity(), location1.getCountry());
        if (newLoc == null) {
            locationRepository.save(location1);
            newLoc = location1;
        }
        newProvider.setLocation(newLoc);
        newProvider.setName(provider.getName());
        newProvider.setEmail(provider.getEmail());
        newProvider.setDescription(provider.getDescription());
        LocalDateTime localDateTime = LocalDateTime.now();
        provider.setLastUpdate(localDateTime);
        ProviderDTO newProviderDTO = providerMapper.ProviderToProviderDTO(newProvider);
        return newProviderDTO;

    }


    @Override
    public void delete(Integer id) {
        Provider provider = providerRepository.findById(id).orElseThrow(() -> new NotFoundException("ServiceProvider not found"));
        providerRepository.delete(provider);
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
                providerRepository.findAll(PageRequest.of(page, 4));
        return serviceProviders;
    }
}
