package com.softserve.demo.repository;

import com.softserve.demo.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends JpaRepository<Location, Integer> {

    @Query("SELECT loc from Location loc where loc.city = :city and loc.country = :country")
    Location findLocationByCityAndCountry(@Param("city")String city, @Param("country")String country);
}
