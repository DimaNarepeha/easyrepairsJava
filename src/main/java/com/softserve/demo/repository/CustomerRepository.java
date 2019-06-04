package com.softserve.demo.repository;

import com.softserve.demo.model.Customer;
import com.softserve.demo.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByEmail(String email);

    Customer findByEmail(String email);

    Customer getCustomerByOffers(Offer offer);

    @Query("SELECT cust FROM Customer cust where cust.user.id = :userId")
    Customer findCustomerByUserId(@Param("userId") Integer id);
}
