package com.softserve.demo.service;

import com.softserve.demo.model.Offer;
import com.softserve.demo.model.Service;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ServiceFromProviders {

    void createService(Service service);

    Service updateService(Service service);

    List<Service> getAllServices();

    void deleteService(Integer id);

    Service getServiceById(Integer id);

    Page<Service> getServicesByPage(int page);

    List<Service> getAllByOffer(Offer offer);
}
