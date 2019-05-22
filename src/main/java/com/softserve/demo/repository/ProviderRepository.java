package com.softserve.demo.repository;

import com.softserve.demo.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {
    boolean existsByEmail(String email);
    Provider getByEmail(String email);
}
