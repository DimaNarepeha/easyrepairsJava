package com.softserve.demo.service;

import com.softserve.demo.dto.LocationDTO;
import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.model.Provider;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProvidersService {

    ProviderDTO findById (Integer id);

    List<ProviderDTO> findAll();

    ProviderDTO save(ProviderDTO providerDTO, LocationDTO locationDTO);

    void delete (Integer id);

    ProviderDTO update (Integer id, ProviderDTO providerDTO, LocationDTO locationDTO);

    void addImageToProviders (Integer id, String fileName);

    Page<Provider> getServiceProvidersByPage(int page);

}
