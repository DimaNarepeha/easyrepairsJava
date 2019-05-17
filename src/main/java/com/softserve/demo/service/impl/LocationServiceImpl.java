package com.softserve.demo.service.impl;

import com.softserve.demo.model.Location;
import com.softserve.demo.repository.LocationRepository;
import com.softserve.demo.service.LocationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public void createLocation(Location location) {
        locationRepository.save(location);
    }

    @Override
    public void updateLocation(Location location) {
        Location locationFromDB = locationRepository.getOne(location.getId());
        if (locationFromDB != null) {
            locationFromDB.setCountry(location.getCountry());
            locationFromDB.setCity(location.getCity());
            locationFromDB.setRegion(location.getRegion());
            locationRepository.save(locationFromDB);
        }
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public void deleteLocation(Integer id) {
        locationRepository.deleteById(id);
    }

    @Override
    public Location getLocationById(Integer id) {
        return locationRepository.getOne(id);
    }

    @Override
    public Page<Location> getLocationsByPage(int page) {
        return locationRepository.findAll(new PageRequest(page, 4));
    }
}
