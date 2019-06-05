package com.softserve.demo.service.impl;


import com.softserve.demo.dto.LocationDTO;
import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.exceptions.NotFoundException;
import com.softserve.demo.model.Location;
import com.softserve.demo.model.Provider;
import com.softserve.demo.model.ProviderStatus;
import com.softserve.demo.model.User;
import com.softserve.demo.repository.LocationRepository;
import com.softserve.demo.repository.ProviderRepository;
import com.softserve.demo.repository.UserRepository;
import com.softserve.demo.service.ProvidersService;
import com.softserve.demo.util.LocationMapper;
import com.softserve.demo.util.Photo;
import com.softserve.demo.util.ProviderMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
    private final ProviderMapper providerMapper;
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;


    public ProvidersServiceImpl(ProviderRepository providerRepository, UserRepository userRepository, LocationRepository locationRepository, ProviderMapper providerMapper, LocationMapper locationMapper) {
        this.providerRepository = providerRepository;
        this.providerMapper = providerMapper;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    @Override
    public ProviderDTO findById(Integer id) {
        Provider provider = providerRepository.findById(id).orElseThrow(() -> new NotFoundException("ServiceProvider not found"));
        return providerMapper.providerToProviderDTO(provider);
    }

    @Override
    public List<ProviderDTO> findAll() {
        return providerRepository.findAll().stream().map(
                providerMapper::providerToProviderDTO).collect(Collectors.toList());
    }

    @Override
    public ProviderDTO save(ProviderDTO providerDTO, LocationDTO locationDTO) {
        Provider provider = providerMapper.providerDTOToProvider(providerDTO);
        Location location = locationMapper.locationDTOToLocation(locationDTO);
        provider.setUser(userRepository.findById(1));

        Location currentLoc = locationRepository.findLocationByCityAndCountry(location.getCity(), location.getCountry(), location.getRegion());
        if (currentLoc == null) {
            locationRepository.save(location);
            provider.setLocation(location);
        } else {
            provider.setLocation(currentLoc);
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        provider.setLastUpdate(localDateTime);
        User user = userRepository.findById(provider.getId());
        user.setImage(Photo.defaultPhoto);
        providerRepository.save(provider);
        return providerMapper.providerToProviderDTO(provider);
    }

    @Override
    public ProviderDTO update(Integer id, ProviderDTO providerDTO, LocationDTO locationDTO) {
        Provider provider = providerMapper.providerDTOToProvider(providerDTO);
        Provider newProvider = providerRepository.findById(id).orElseThrow(() -> new NotFoundException("ServiceProvider not found"));
        Location location1 = locationMapper.locationDTOToLocation(locationDTO);
        Location newLoc = locationRepository.findLocationByCityAndCountry(location1.getCity(), location1.getCountry(), location1.getRegion());
        if (newLoc == null) {
            locationRepository.save(location1);
            newLoc = location1;
        }
        newProvider.setLocation(newLoc);
        newProvider.setName(provider.getName());
        User user = userRepository.findById(provider.getId());
        user.setEmail(providerDTO.getEmail());
        newProvider.setDescription(provider.getDescription());
        LocalDateTime localDateTime = LocalDateTime.now();
        provider.setLastUpdate(localDateTime);
        return providerMapper.providerToProviderDTO(newProvider);
    }


    @Override
    public void delete(Integer id) {
        Provider provider = providerRepository.findById(id).orElseThrow(() -> new NotFoundException("ServiceProvider not found"));
        providerRepository.delete(provider);
    }

    @Override
    public void addImageToProviders(Integer id, String fileName) {
        Provider provider =
                providerRepository.findById(id).get();
        User user = userRepository.findById(provider.getUser().getId());
        user.setImage(fileName);
        providerRepository.save(provider);
    }

    @Override
    public Page<Provider> getServiceProvidersByPage(int page) {
        return providerRepository.findAll(PageRequest.of(page, 4));
    }

    @Override
    public Page<?> getServiceProvidersByStatus(int page, int numberOfProvidersOnPage, ProviderStatus status) {
        return providerRepository.findByStatus(status, PageRequest.of(page, numberOfProvidersOnPage));
    }

    @Override
    public ProviderDTO updateStatus(Integer id, String status) {
        Provider provider = providerRepository.findById(id).orElseThrow(() -> new NotFoundException("ServiceProvider not found"));
        provider.setStatus(ProviderStatus.valueOf(status));
        providerRepository.save(provider);
        return providerMapper.providerToProviderDTO(provider);
    }

    @Override
    public <T> Page<Provider> findAll(Specification<T> approved, int page, int numberOfProvidersOnPage, String sortBy) {
        return providerRepository.findAll(approved, PageRequest.of(page, numberOfProvidersOnPage, Sort.by(sortBy).descending()));

    }
}
