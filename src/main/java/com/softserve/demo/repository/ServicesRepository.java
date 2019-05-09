package com.softserve.demo.repository;

import com.softserve.demo.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicesRepository extends JpaRepository<Service, Long> {
}
