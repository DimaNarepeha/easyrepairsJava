package com.softserve.demo.filter.specification;

import com.softserve.demo.model.Provider;
import com.softserve.demo.model.ProviderStatus;
import com.softserve.demo.model.Service;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.List;


public class ProviderSpecification {

    public static Specification<Provider> isApproved() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), ProviderStatus.APPROVED);
    }

    public static Specification<Provider> isCity(String city) {
        return (root, query, criteriaBuilder) ->
                (city == null) ? null
                        : criteriaBuilder.equal(root.get("location").get("city"), city);
    }

    public static Specification<Provider> isRegion(String region) {
        return (root, query, criteriaBuilder) ->
                (region == null) ? null
                        : criteriaBuilder.equal(root.get("location").get("region"), region);
    }

    public static Specification<Provider> isCountry(String country) {
        return (root, query, criteriaBuilder) ->
                (country == null) ? null
                        : criteriaBuilder.equal(root.get("location").get("country"), country);
    }

    public static Specification<Provider> greaterThanOrEqualToRating(int minRating) {
        return (root, query, criteriaBuilder) ->
                (minRating == 0) ? null
                        : criteriaBuilder.greaterThanOrEqualTo(root.get("raiting"), minRating);
    }

    private static Specification<Provider> isMemberService(Service service) {
        return (root, query, criteriaBuilder) ->
                (service == null) ? null
                        : criteriaBuilder.isMember(service, root.get("services"));

    }

    public static Specification<Provider> buildIsMemberServices(List<Service> services) {
        if ( CollectionUtils.isEmpty(services)) {
            return null;
        }
        Specification<Provider> result = ProviderSpecification.isMemberService(services.get(0));
        for (int i = 1; i < services.size(); i++) {
            result = Specification.where(result).and(ProviderSpecification.isMemberService(services.get(i)));
        }
        return result;
    }

    public static Specification<Provider> isStatus(ProviderStatus status) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Provider> likeName (String name) {
        return (root, query, criteriaBuilder) ->
                (name == null) ? null
                        : criteriaBuilder.like(root.get("name"),"%"+name+"%");
    }
}
