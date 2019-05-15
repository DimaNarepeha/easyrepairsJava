package com.softserve.demo.service;

import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.model.Provider;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProvidersService {

    Provider findById (Integer id);

    List<Provider> findAll();

    ProviderDTO save(ProviderDTO providerDTO);

    void delete (Integer id);

    ProviderDTO update (Integer id, ProviderDTO providerDTO);

    void addImageToProviderds (Integer id, String fileName);

    Page<Provider> getServiceProvidersByPage(int page);

}
