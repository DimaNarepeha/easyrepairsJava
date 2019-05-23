package com.softserve.demo.filter;

import com.softserve.demo.dto.ServiceProviderInfoDTO;
import com.softserve.demo.filter.specification.ProviderSpecification;
import com.softserve.demo.model.Provider;
import com.softserve.demo.model.ProviderStatus;
import com.softserve.demo.model.search.ProviderCriteria;
import com.softserve.demo.service.impl.ProvidersServiceImpl;
import com.softserve.demo.util.ProviderInfoMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProviderFilter {

    private final ProviderInfoMapper providerMapper;
    private final ProvidersServiceImpl providersService;

    public ProviderFilter(ProvidersServiceImpl providersService, ProviderInfoMapper providerMapper) {
        this.providersService = providersService;
        this.providerMapper = providerMapper;
    }

    public List<ServiceProviderInfoDTO> findByListServices(ProviderCriteria criteria) {
        return providerMapper.map(findByCriteria(criteria.getLocation(), criteria.getMinRating())
                .stream()
                .filter(p -> p.getServices().containsAll(criteria.getServices()))
                .collect(Collectors.toList()));
    }

    private List<Provider> findByCriteria(String city, int minRaiting) {
        return providersService.findAll(Specification.where(ProviderSpecification.isStatus())
                .and(ProviderSpecification.isCity(city))
                .and(ProviderSpecification.greaterThanRaiting(minRaiting)));

    }

    public List<Provider> findAllApproved() {
        return providersService.findAllByStatus(ProviderStatus.APPROVED);
    }

}
