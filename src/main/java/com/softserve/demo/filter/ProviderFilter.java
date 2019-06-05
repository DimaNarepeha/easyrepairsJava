package com.softserve.demo.filter;

import com.softserve.demo.dto.ProviderInfoDTO;
import com.softserve.demo.filter.specification.ProviderSpecification;
import com.softserve.demo.model.Provider;
import com.softserve.demo.model.search.ProviderCriteria;
import com.softserve.demo.service.impl.ProvidersServiceImpl;
import com.softserve.demo.service.impl.ServiceFromProvidersImpl;
import com.softserve.demo.util.ProviderInfoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProviderFilter {

    private final ProviderInfoMapper providerMapper;
    private final ProvidersServiceImpl providersService;
    private final ServiceFromProvidersImpl serviceFromProviders;

    public ProviderFilter(ProvidersServiceImpl providersService, ProviderInfoMapper providerMapper, ServiceFromProvidersImpl serviceFromProviders) {
        this.providersService = providersService;
        this.providerMapper = providerMapper;
        this.serviceFromProviders = serviceFromProviders;
    }

    private List<com.softserve.demo.model.Service> getCheckedServices(String checkedServicesId) {
        int one = 1;
        int two = 2;
        List<com.softserve.demo.model.Service> services = new ArrayList<>();
        if (checkedServicesId != null && checkedServicesId.length() > two) {
            String s = checkedServicesId.substring(one, checkedServicesId.length() - one);
            for (String serviceId : s.split(",")) {
                services.add(serviceFromProviders.getServiceById((Integer.parseInt(serviceId))));
            }
        }
        return services;
    }

    public Page<ProviderInfoDTO> pageFindByCriteria(int page, int numberOfProvidersOnPage, Map<String, String> searchParam) {
        ProviderCriteria searchCriteria = new ProviderCriteria();
        searchCriteria.setCity(searchParam.get("city"));
        searchCriteria.setRegion(searchParam.get("region"));
        searchCriteria.setCountry(searchParam.get("country"));
        searchCriteria.setMinRating(searchParam.get("minRating"));
        searchCriteria.setSortBy(searchParam.get("sortBy"));
        searchCriteria.setCheckedServices(getCheckedServices(searchParam.get("checkedServices")));
        Page<Provider> entityPage = providersService.findAll(Specification.where(ProviderSpecification.isApproved())
                        .and(ProviderSpecification.isCity(searchCriteria.getCity()))
                        .and(ProviderSpecification.isRegion(searchCriteria.getRegion()))
                        .and(ProviderSpecification.isCountry(searchCriteria.getCountry()))
                        .and(ProviderSpecification.greaterThanOrEqualToRating(searchCriteria.getMinRating()))
                        .and(ProviderSpecification.buildIsMemberServices(searchCriteria.getCheckedServices())),
                page, numberOfProvidersOnPage, searchCriteria.getSortBy());
        List<ProviderInfoDTO> providerInfoDTOS = providerMapper.convertToDTOs(entityPage.getContent());
        return new PageImpl<>(providerInfoDTOS, PageRequest.of(page, numberOfProvidersOnPage), entityPage.getTotalElements());
    }
}

