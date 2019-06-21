package com.softserve.demo.repository;

import com.softserve.demo.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServicesRepository extends JpaRepository<Service, Integer> {

    Service findByServiceName (String serviceName);

}
