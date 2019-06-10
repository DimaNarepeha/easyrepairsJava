package com.softserve.demo.repository;

import com.softserve.demo.model.Customer;
import com.softserve.demo.model.CustomerStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Page<Customer> findByStatus(CustomerStatus status, Pageable pageable);

    <T> Page<Customer> findAll(Specification<T> approved, Pageable pageable);

    Customer getCustomerByUserId(Integer id);

    Customer findCustomerById(Integer id);
}
