package com.softserve.demo.service.impl;

import com.softserve.demo.exceptions.NotFoundException;
import com.softserve.demo.model.Location;
import com.softserve.demo.repository.LocationRepository;
import com.softserve.demo.service.LocationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Illia Chenchak
 */
@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    private static final String LOCATION_NOT_FOUND = "Location with id [%s] was not found!";
    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location findById(Integer idLocation) {
        return locationRepository.findById(idLocation).orElseThrow(() -> new NotFoundException(String.format(LOCATION_NOT_FOUND, idLocation)));
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
    public void delete(Integer idLocation) {
        locationRepository.delete(locationRepository.findById(idLocation).orElseThrow(() -> new NotFoundException(String.format(LOCATION_NOT_FOUND, idLocation))));
    }

    @Override
    public Location saveLocationIfNotExist(Location location) {
        Location locationFromDB = locationRepository.findLocationByCityAndCountryAndRegion(
                location.getCity(), location.getCountry(), location.getRegion());

        if (locationFromDB == null) {
            return locationRepository.save(location);
        }
        return locationFromDB;
    }
}
