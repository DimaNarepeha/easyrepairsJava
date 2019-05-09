package com.softserve.demo.service;

import com.softserve.demo.model.Service;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ServiceFromProviders {

    void createService(Service service);

    com.softserve.demo.model.Service updateService(Long id, Service service);

    List<Service> getAllServices();

    Service deleteService(Long id);

    Service getServiceById(Long id);

    Page<Service> getServicesByPage(int page);
}
