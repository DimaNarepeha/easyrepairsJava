package com.softserve.demo.service.impl;

import com.softserve.demo.dto.LocationDTO;
import com.softserve.demo.model.Location;
import com.softserve.demo.repository.LocationRepository;
import com.softserve.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Illia Chenchak
 */
@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;


    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location findById(Integer id) {
        return locationRepository.findById(id).get();
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location save(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public void delete(Integer id) {
        locationRepository.delete(locationRepository.findById(id).get());
    }




}
