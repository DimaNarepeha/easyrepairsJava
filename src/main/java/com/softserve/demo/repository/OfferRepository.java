package com.softserve.demo.repository;

import com.softserve.demo.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

    List<Offer> getAllByCustomer_Id(Integer id);
}
