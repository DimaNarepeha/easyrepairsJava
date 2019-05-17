package com.softserve.demo.repository;

import com.softserve.demo.dto.ServiceProviderInfoDTO;
import com.softserve.demo.model.Provider;
import com.softserve.demo.model.ProviderStatus;
import com.softserve.demo.model.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvidersRepository extends JpaRepository<Provider, Integer> {

    <T> List<Provider> findAll(Specification<T> approved);
    List<Provider> findAllByStatus(ProviderStatus status);
    List<Provider> findAllByServices(Service service);
    List<Provider> findAllByServices(List<Service> services);

   // List<Provider> findAllByOrderByNameAsc();


}
