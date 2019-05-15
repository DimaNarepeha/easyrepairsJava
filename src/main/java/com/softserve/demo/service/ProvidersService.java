package com.softserve.demo.service;

import com.softserve.demo.model.Provider;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProvidersService {

    Provider findById (Integer id);

    List<Provider> findAll();

    Provider save(Provider providers);

    void delete (Integer id);

    Provider update (Integer id, Provider providers);

    void addImageToCustomer(Integer id, String fileName);

    public Page<Provider> getServiceProvidersByPage(int page);

}
