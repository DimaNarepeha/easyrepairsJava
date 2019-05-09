package com.softserve.demo.repository;

import com.softserve.demo.model.Providers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvidersRepository extends JpaRepository<Providers, Integer> {
}
