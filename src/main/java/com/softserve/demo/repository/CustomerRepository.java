package com.softserve.demo.repository;

import com.softserve.demo.model.Customer;
import com.softserve.demo.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByEmail(String email);
    Customer getCustomerByOffers(Offer offer);
}
