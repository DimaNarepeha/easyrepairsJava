package com.softserve.demo.service;

import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.model.Provider;
import com.softserve.demo.model.ProviderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ProvidersService {

    Provider findById (Integer id);

    List<Provider> findAll();

    void delete (Integer id);

    ProviderDTO save(ProviderDTO providerDTO);

    ProviderDTO update (Integer id, ProviderDTO providerDTO);

    void addImageToProviderds (Integer id, String fileName);

    Page<Provider> getServiceProvidersByPage(int page);

    <T> List<Provider> findAll(Specification<T> approved);

    List<Provider> findAllByStatus(ProviderStatus status);

    Page<?> getServiceProvidersByStatus(int page, int numberOfProvidersOnPage ,ProviderStatus status);
}
