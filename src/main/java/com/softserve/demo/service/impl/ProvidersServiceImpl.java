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
import com.softserve.demo.util.mappers.LocationMapper;
import com.softserve.demo.util.mappers.ProviderMapper;
import com.softserve.demo.util.Constant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


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

    private Location setOrSaveLocation(Location location) {
        Location newLoc = locationRepository.findLocationByCityAndCountryAndRegion(location.getCity(), location.getCountry(), location.getRegion());
        if (newLoc == null) {
            locationRepository.save(location);
            newLoc = location;
        }
        return newLoc;
    }

    @Override
    public ProviderDTO findById(Integer idProvider) {
        Provider provider = providerRepository.findById(idProvider).orElseThrow(() -> new NotFoundException("ServiceProvider not found"));
        return providerMapper.providerToProviderDTO(provider);
    }

    @Override
    public List<ProviderDTO> findAll() {
        return providerRepository.findAll().stream().map(
                providerMapper::providerToProviderDTO).collect(Collectors.toList());
    }

    @Override
    public ProviderDTO save(ProviderDTO providerDTO) {
        Provider provider = providerMapper.providerDTOToProvider(providerDTO);
        Location location = locationMapper.locationDTOToLocation(providerDTO.getLocation());
        provider.setLocation(setOrSaveLocation(location));
        LocalDateTime localDateTime = LocalDateTime.now();
        provider.setLastUpdate(localDateTime);
        User user = userRepository.findById(provider.getId());
        user.setImage(Constant.defaultPhoto);
        providerRepository.save(provider);
        return providerMapper.providerToProviderDTO(provider);
    }

    @Override
    public ProviderDTO update(ProviderDTO providerDTO) {
        Provider provider = providerMapper.providerDTOToProvider(providerDTO);
        Provider newProvider = providerRepository.findById(provider.getId()).orElseThrow(() -> new NotFoundException("ServiceProvider not found"));
        Location location = locationMapper.locationDTOToLocation(providerDTO.getLocation());
        newProvider.setLocation(setOrSaveLocation(location));
        newProvider.setName(provider.getName());
        User user = userRepository.findById(provider.getId());
        user.setEmail(providerDTO.getEmail());
        newProvider.setDescription(provider.getDescription());
        LocalDateTime localDateTime = LocalDateTime.now();
        provider.setLastUpdate(localDateTime);
        return providerMapper.providerToProviderDTO(newProvider);
    }


    @Override
    public void delete(Integer idProvider) {
        Provider provider = providerRepository.findById(idProvider).orElseThrow(() -> new NotFoundException("ServiceProvider not found"));
        providerRepository.delete(provider);
    }

    @Override
    public void addImageToProviders(Integer idProvider, String fileName) {
        Provider provider =
                providerRepository.findById(idProvider).get();
        User user = userRepository.findById(provider.getUser().getId());
        user.setImage(fileName);
        providerRepository.save(provider);
    }

    @Override
    public Page<ProviderDTO> getServiceProvidersByPage(Pageable pageable) {
        return providerRepository.findByStatus(ProviderStatus.APPROVED, pageable)
                .map(providerMapper::providerToProviderDTO);
    }

    @Override
    public Page<ProviderDTO> getServiceProvidersByStatus(Pageable pageable, ProviderStatus status) {
        return providerRepository.findByStatus(status, pageable)
                .map(providerMapper::providerToProviderDTO);
    }

    @Override
    public ProviderDTO updateStatus(Integer idProvider, String status) {
        Provider provider = providerRepository.findById(idProvider).orElseThrow(() -> new NotFoundException("ServiceProvider not found"));
        provider.setStatus(ProviderStatus.valueOf(status));
        providerRepository.save(provider);
        return providerMapper.providerToProviderDTO(provider);
    }

    @Override
    public ProviderDTO findProvidersByUserId(Integer idUser) {
        return providerMapper.providerToProviderDTO(providerRepository.findByUserId(idUser));
    }

    @Override
    public <T> Page<Provider> findAll(Specification<T> approved, int page, int numberOfProvidersOnPage, String sortBy) {
        return providerRepository.findAll(approved, PageRequest.of(page, numberOfProvidersOnPage, Sort.by(sortBy).descending()));
    }

    @Override
    public ProviderDTO findByName(String providerName) {
        Provider provider = providerRepository.findByName(providerName).orElseThrow(() -> new NotFoundException("ServiceProvider not found"));
        return providerMapper.providerToProviderDTO(provider);
    }
}
