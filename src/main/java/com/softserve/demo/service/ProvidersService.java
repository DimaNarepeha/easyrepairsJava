package com.softserve.demo.service;

import com.softserve.demo.dto.LocationDTO;
import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.model.Provider;
import com.softserve.demo.model.ProviderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ProvidersService {

    ProviderDTO findById (Integer id);

    List<ProviderDTO> findAll();

    ProviderDTO save(ProviderDTO providerDTO, LocationDTO locationDTO);

    void delete (Integer id);

    ProviderDTO update (Integer id, ProviderDTO providerDTO, LocationDTO locationDTO);

    void addImageToProviders (Integer id, String fileName);

    Page<Provider> getServiceProvidersByPage(int page);

    <T> List<Provider> findAll(Specification<T> approved);

    List<Provider> findAllByStatus(ProviderStatus status);

    Page<?> getServiceProvidersByStatus(int page, int numberOfProvidersOnPage ,ProviderStatus status);
}
