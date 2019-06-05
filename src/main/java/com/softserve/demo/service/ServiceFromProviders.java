package com.softserve.demo.service;

import com.softserve.demo.model.Service;

import java.util.List;

public interface ServiceFromProviders {

    List<Service> getAllServices();

    Service getServiceById(Integer id);
}
