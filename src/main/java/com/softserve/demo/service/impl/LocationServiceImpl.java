package com.softserve.demo.service.impl;

import com.softserve.demo.model.Location;
import com.softserve.demo.repository.LocationRepository;
import com.softserve.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    
    @Autowired
    LocationRepository locationRepository;
    
    @Override
    public void createLocation(Location location) {
        locationRepository.save(location);
    }

    @Override
    public Location updateLocation(Integer id, Location location) {
        if (locationRepository.existsById(id)) {
            Location locationFromDB = locationRepository.getOne(id);
            locationFromDB.setCountry(location.getCountry());
            locationFromDB.setCity(location.getCity());
            locationFromDB.setRegion(location.getRegion());
            locationRepository.save(locationFromDB);
            return locationRepository.getOne(id);
        }
        return null;
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location deleteLocation(Integer id) {
        if (locationRepository.existsById(id)) {
            Location locationFromDB = locationRepository.getOne(id);
            locationRepository.deleteById(id);
            return locationFromDB;
        }
        return null;
    }

    @Override
    public Location getLocationById(Integer id) {
        if (locationRepository.existsById(id)) {
            return locationRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Page<Location> getLocationsByPage(int page) {
        return locationRepository.findAll(new PageRequest(page, 4));
    }
}
