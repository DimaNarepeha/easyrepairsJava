package com.softserve.demo.filter.specification;

import com.softserve.demo.model.Provider;
import com.softserve.demo.model.ProviderStatus;
import org.springframework.data.jpa.domain.Specification;

public class ProviderSpecification {
    public static Specification<Provider> isStatus() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), ProviderStatus.APPROVED);
    }

    public static Specification<Provider> isCity(String city) {
        return (root, query, criteriaBuilder) ->
                (city == null) ? null
                        : criteriaBuilder.equal(root.get("location").get("city"), city);
    }

    public static Specification<Provider> greaterThanRaiting(int minRaiting) {
        return (root, query, criteriaBuilder) ->
                (minRaiting == 0) ? null
                        : criteriaBuilder.greaterThan(root.get("raiting"), minRaiting);
    }


}
