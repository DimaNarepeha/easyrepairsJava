package com.softserve.demo.repository;

import com.softserve.demo.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

    @Query("SELECT off from Offer off where off.customer.id = :id")
    List<Offer> findAllByCustomerId(@Param("id") Integer id);
}
