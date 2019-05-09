package com.softserve.demo.repository;

import com.softserve.demo.model.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvidersRepository extends JpaRepository<ServiceProvider, Integer> {
}
