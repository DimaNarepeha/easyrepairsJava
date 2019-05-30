package com.softserve.demo.service;

import com.softserve.demo.model.Location;

import java.util.List;

public interface LocationService {

    Location findById(Integer id);

    List<Location> findAll();

    Location save(Location location);

    void delete(Integer id);
}
