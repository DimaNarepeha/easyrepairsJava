package com.softserve.demo.filter;

import com.softserve.demo.filter.specification.ProviderSpecification;
import com.softserve.demo.model.Provider;
import com.softserve.demo.model.search.ProviderCriteria;
import com.softserve.demo.service.impl.ProvidersServiceImpl;
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
        return findByAllCriteria(criteria)
                .stream()
                .filter(p -> p.getServices().containsAll(criteria.getServices()))
                .collect(Collectors.toList());
    }

    public List<Provider> findByAllCriteria(ProviderCriteria criteria) {
        return providersService.findAll(Specification.where(ProviderSpecification.isStatus(criteria.getStatus()))
                .and(ProviderSpecification.isCity(criteria.getCity()))
                .and(ProviderSpecification.betweenRangeRaiting(criteria.getMinRaiting(), criteria.getMaxRaiting())));

    }

}
