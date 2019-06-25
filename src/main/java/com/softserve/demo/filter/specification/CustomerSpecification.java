package com.softserve.demo.filter.specification;

import com.softserve.demo.model.Customer;
import com.softserve.demo.model.CustomerStatus;
import com.softserve.demo.model.Provider;
import com.softserve.demo.model.ProviderStatus;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecification {
    public static Specification<Customer> likeFirstName(String firstName) {
        return ((root, query, criteriaBuilder) -> firstName.isEmpty() ? null : criteriaBuilder
                .like(root.get("firstName"), "%" + firstName + "%"));
    }

    public static Specification<Customer> likeLastName(String lastName) {
        return ((root, query, criteriaBuilder) -> lastName.isEmpty() ? null : criteriaBuilder
                .like(root.get("lastName"), "%" + lastName + "%"));
    }

    public static Specification<Customer> isStatus(CustomerStatus status) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), status);
    }

}
