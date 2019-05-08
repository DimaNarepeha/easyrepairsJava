package com.softserve.demo.service;

import com.softserve.demo.model.Providers;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProvidersService {

    Providers findById (Integer id);

    List<Providers> findAll();

    Providers save(Providers providers);

    void delete (Integer id);

    Providers update (Integer id, Providers providers);

    void addImageToCustomer(Integer id, String fileName);

    public Page<Providers> getServiceProvidersByPage(int page);

}
