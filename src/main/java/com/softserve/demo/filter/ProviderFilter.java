package com.softserve.demo.filter;

import com.softserve.demo.filter.specification.ProviderSpecification;
import com.softserve.demo.model.Provider;
import com.softserve.demo.model.ProviderStatus;
import com.softserve.demo.model.search.ProviderCriteria;
import com.softserve.demo.service.impl.ProvidersServiceImpl;
import com.softserve.demo.util.ProviderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProviderFilter {



    private final ProvidersServiceImpl providersService;

    public ProviderFilter(ProvidersServiceImpl providersService) {
        this.providersService = providersService;
    }

    public List<Provider> findByListServices(ProviderCriteria criteria) {
        return findByCriteria(criteria.getStatus(), criteria.getCity(), criteria.getMinRaiting(), criteria.getMaxRaiting())
                .stream()
                .filter(p -> p.getServices().containsAll(criteria.getServices()))
                .collect(Collectors.toList());
    }

    public List<Provider> findByCriteria(ProviderStatus status, String city,  int minRaiting, int maxRaiting) {
        return providersService.findAll(Specification.where(ProviderSpecification.isStatus(status))
                .and(ProviderSpecification.isCity(city))
                .and(ProviderSpecification.betweenRangeRaiting(minRaiting, maxRaiting)));

    }

    public List<Provider> findAllApproved(){
        return providersService.findAllByStatus(ProviderStatus.APPROVED);
    }

}
