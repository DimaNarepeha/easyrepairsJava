package com.softserve.demo.repository;

import com.softserve.demo.model.Customer;
import com.softserve.demo.model.CustomerStatus;
import com.softserve.demo.model.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Page<Customer> findByStatus(CustomerStatus status, Pageable pageable);

    Customer getCustomerByOffers(Offer offer);

    @Query("SELECT cust FROM Customer cust where cust.user.id = :userId")
    Customer findCustomerByUserId(@Param("userId") Integer id);
}
