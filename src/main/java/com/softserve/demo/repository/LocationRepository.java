package com.softserve.demo.repository;

import com.softserve.demo.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends JpaRepository<Location, Integer> {

    Location findLocationByCityAndCountryAndRegion(String city, String country,String region);
}
