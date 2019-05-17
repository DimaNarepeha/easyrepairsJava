package com.softserve.demo.repository;

import com.softserve.demo.model.Provider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {
    boolean existsByEmail(String email);
    <T> List<Provider> findAll(Specification<T> approved);
}
