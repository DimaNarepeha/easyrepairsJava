package com.softserve.demo.service;

import com.softserve.demo.model.Location;
import com.softserve.demo.model.Offer;

import java.util.List;

public interface LocationService {

    Location findById(Integer id);

    List<Location> findAll();

    Location save(Location location);

    void delete(Integer id);

    Location getLocationByOffer(Offer offer);
}
