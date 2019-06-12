package com.softserve.demo.service.impl;


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
import com.softserve.demo.util.Constant;
import com.softserve.demo.util.mappers.LocationMapper;
import com.softserve.demo.util.mappers.ProviderMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    private static final String PROVIDER_NOT_FOUND = "Provider with id [%s] was not found!";

    private final ProviderRepository providerRepository;
    private final UserRepository userRepository;
    private final ProviderMapper providerMapper;
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;


    public ProvidersServiceImpl(final ProviderRepository providerRepository, final UserRepository userRepository,
                                final LocationRepository locationRepository, final ProviderMapper providerMapper,
                                final LocationMapper locationMapper) {
        this.providerRepository = providerRepository;
        this.providerMapper = providerMapper;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    private Location setOrSaveLocation(final Location location) {
        Location newLoc = locationRepository.findLocationByCityAndCountryAndRegion(location.getCity(), location.getCountry(), location.getRegion());
        if (newLoc == null) {
            locationRepository.save(location);
            newLoc = location;
        }
        return newLoc;
    }

    @Override
    public ProviderDTO findById(final Integer idProvider) {
        Provider provider = providerRepository.findById(idProvider).orElseThrow(() -> new NotFoundException(String.format(PROVIDER_NOT_FOUND, idProvider)));
        return providerMapper.providerToProviderDTO(provider);
    }

    @Override
    public List<ProviderDTO> findAll() {
        return providerRepository.findAll().stream().map(
                providerMapper::providerToProviderDTO).collect(Collectors.toList());
    }

    @Override
    public ProviderDTO save(final ProviderDTO providerDTO) {
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
    public ProviderDTO update(final ProviderDTO providerDTO) {
        Provider provider = providerMapper.providerDTOToProvider(providerDTO);
        Provider newProvider = providerRepository.findById(provider.getId()).orElseThrow(() -> new NotFoundException("ServiceProvider not found"));
        Location location = locationMapper.locationDTOToLocation(providerDTO.getLocation());
        newProvider.setLocation(setOrSaveLocation(location));
        newProvider.setName(provider.getName());
        User user = userRepository.findById(provider.getUser().getId());
        user.setEmail(providerDTO.getEmail());
        newProvider.setDescription(provider.getDescription());
        LocalDateTime localDateTime = LocalDateTime.now();
        newProvider.setLastUpdate(localDateTime);
        newProvider.setStatus(ProviderStatus.MODIFIED);
        return providerMapper.providerToProviderDTO(newProvider);
    }


    @Override
    public void delete(final Integer idProvider) {
        Provider provider = providerRepository.findById(idProvider).orElseThrow(() -> new NotFoundException(String.format(PROVIDER_NOT_FOUND, idProvider)));
        providerRepository.delete(provider);
    }

    @Override
    public void addImageToProviders(final Integer idProvider, final String fileName) {
        Provider provider =
                providerRepository.findById(idProvider).orElseThrow(() -> new NotFoundException(String.format(PROVIDER_NOT_FOUND, idProvider)));
        User user = userRepository.findById(provider.getUser().getId());
        user.setImage(fileName);
        providerRepository.save(provider);
    }

    @Override
    public Page<ProviderDTO> getServiceProvidersByPage(final Pageable pageable) {
        return providerRepository.findByStatus(ProviderStatus.APPROVED, pageable)
                .map(providerMapper::providerToProviderDTO);
    }

    @Override
    public Page<ProviderDTO> getServiceProvidersByStatus(final Pageable pageable, final ProviderStatus status) {
        return providerRepository.findByStatus(status, pageable)
                .map(providerMapper::providerToProviderDTO);
    }

    @Override
    public ProviderDTO updateStatus(final Integer idProvider, final String status) {
        Provider provider = providerRepository.findById(idProvider).orElseThrow(() -> new NotFoundException(String.format(PROVIDER_NOT_FOUND, idProvider)));
        provider.setStatus(ProviderStatus.valueOf(status));
        providerRepository.save(provider);
        return providerMapper.providerToProviderDTO(provider);
    }

    @Override
    public ProviderDTO findProvidersByUserId(final Integer idUser) {
        return providerMapper.providerToProviderDTO(providerRepository.findByUserId(idUser));
    }

    @Override
    public <T> Page<Provider> findAll(final Specification<T> approved, final int page, final int numberOfProvidersOnPage,
                                      final String sortBy) {
        return providerRepository.findAll(approved, PageRequest.of(page, numberOfProvidersOnPage, Sort.by(sortBy).descending()));
    }
}
