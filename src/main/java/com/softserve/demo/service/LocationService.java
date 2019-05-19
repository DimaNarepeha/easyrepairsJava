package com.softserve.demo.service;

import com.softserve.demo.model.Location;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LocationService {
    
    void createLocation(Location location);

    void updateLocation(Location location);

    List<Location> getAllLocations();

    void deleteLocation(Integer id);

    Location getLocationById(Integer id);

    Page<Location> getLocationsByPage(int page);
}
