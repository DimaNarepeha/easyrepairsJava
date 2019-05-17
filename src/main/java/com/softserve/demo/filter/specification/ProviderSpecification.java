package com.softserve.demo.filter.specification;

import com.softserve.demo.model.Provider;
import com.softserve.demo.model.ProviderStatus;
import org.springframework.data.jpa.domain.Specification;

public class ProviderSpecification {
    public static Specification<Provider> isStatus(ProviderStatus status) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Provider> isCity(String city) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("location").get("city"), city);
    }

    public static Specification<Provider> betweenRangeRaiting(final int minRaiting, final int maxRaiting) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("raiting"), minRaiting, maxRaiting);
    }

}
