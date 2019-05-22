package com.softserve.demo.service;

import com.softserve.demo.model.Location;
import com.softserve.demo.model.Offer;

import java.util.List;

public interface LocationService {
    
    void createLocation(Location location);

    Location updateLocation(Location location);

    List<Location> getAllLocations();

    void deleteLocation(Integer id);

    Location getLocationById(Integer id);

    Location getLocationByOffer(Offer offer);
}
