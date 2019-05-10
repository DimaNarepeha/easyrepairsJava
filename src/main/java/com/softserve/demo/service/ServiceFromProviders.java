package com.softserve.demo.service;

import com.softserve.demo.model.Service;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ServiceFromProviders {

    void createService(Service service);

    Service updateService(Integer id, Service service);

    List<Service> getAllServices();

    Service deleteService(Integer id);

    Service getServiceById(Integer id);

    Page<Service> getServicesByPage(int page);
}
