package com.softserve.demo.repository;

import com.softserve.demo.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {

    Location findLocationByCityAndCountryAndRegion(String city, String country, String region);
}

