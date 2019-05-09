package com.softserve.demo.service;

import com.softserve.demo.model.ServiceProvider;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProvidersService {

    ServiceProvider findById (Integer id);

    List<ServiceProvider> findAll();

    ServiceProvider save(ServiceProvider providers);

    void delete (Integer id);

    ServiceProvider update (Integer id, ServiceProvider providers);

    void addImageToCustomer(Integer id, String fileName);

    public Page<ServiceProvider> getServiceProvidersByPage(int page);

}
