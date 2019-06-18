package com.softserve.demo.service;

import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.dto.ServiceDTO;
import com.softserve.demo.model.Service;

import java.util.List;

public interface ServiceFromProviders {

    List<Service> getAllServices();

    Service getServiceById(Integer id);

    ServiceDTO saveServiceFromProvider (Integer id, ServiceDTO serviceDTO);

    List<ServiceDTO> findAllServicesNotPresentInProviders(Integer idProvider);

    List<ServiceDTO> deleteByServiceNameInProvider (Integer idProvider, String serviceName);
}
