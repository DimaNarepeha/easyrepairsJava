package com.softserve.demo.service;

import com.softserve.demo.model.Location;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LocationService {
    
    void createLocation(Location location);

    Location updateLocation(Integer id, Location location);

    List<Location> getAllLocations();

    Location deleteLocation(Integer id);

    Location getLocationById(Integer id);

    Page<Location> getLocationsByPage(int page);
}
