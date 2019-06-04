package com.softserve.demo.repository;

import com.softserve.demo.model.Provider;
import com.softserve.demo.model.ProviderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {

    Page<Provider> findByStatus(ProviderStatus status, Pageable pageable);

    boolean existsByEmail(String email);

    Provider getByEmail(String email);

    @Query("SELECT provider from Provider provider where provider.user.id = :userId")

    Provider findByUserId(@Param("userId") Integer id);
}
