package com.softserve.demo.repository;

import com.softserve.demo.model.Provider;
import com.softserve.demo.model.ProviderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {

    Page<Provider> findByStatus(ProviderStatus status, Pageable pageable);

    Provider findByUserId(Integer id);

    <T> Page<Provider> findAll(Specification<T> approved, Pageable pageable);
}

