package com.softserve.demo.repository;

import com.softserve.demo.model.Offer;
import com.softserve.demo.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicesRepository extends JpaRepository<Service, Integer> {

    List<Service> getAllByOffers(Offer offer);
}
