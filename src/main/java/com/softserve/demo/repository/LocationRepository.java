package com.softserve.demo.repository;

import com.softserve.demo.model.Location;
import com.softserve.demo.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends JpaRepository<Location, Integer> {

    Location getLocationByOffers(Offer offer);

    @Query("SELECT loc from Location loc where loc.city = :city and loc.country = :country and loc.region = :region")
    Location findLocationByCityAndCountry(@Param("city")String city,
                                          @Param("country")String country, @Param("region")String region);
}
