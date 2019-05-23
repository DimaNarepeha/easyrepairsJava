package com.softserve.demo.repository;

import com.softserve.demo.model.Provider;
import com.softserve.demo.model.ProviderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {
    Page<Provider> findByStatus(ProviderStatus status,Pageable pageable);
    boolean existsByEmail(String email);
    <T> List<Provider> findAll(Specification<T> approved);
    List<Provider> findAllByStatus(ProviderStatus status);
    Provider getByEmail(String email);

}

