package com.softserve.demo.service;

import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.model.Provider;
import com.softserve.demo.model.ProviderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ProvidersService {

    ProviderDTO findById(Integer id);

    List<ProviderDTO> findAll();

    ProviderDTO save(ProviderDTO providerDTO);

    void delete(Integer id);

    ProviderDTO update(ProviderDTO providerDTO);

    void addImageToProviders(Integer id, String fileName);

    Page<ProviderDTO> getServiceProvidersByPage(Pageable pageable);

    Page<ProviderDTO> getServiceProvidersByStatus(int page, int numberOfProvidersOnPage, ProviderStatus status);

    ProviderDTO updateStatus(Integer id, String status);

    ProviderDTO findProvidersByUserId(Integer id);

    <T> Page<Provider> findAll(Specification<T> approved, int page, int numberOfProvidersOnPage, String sortBy);

}
