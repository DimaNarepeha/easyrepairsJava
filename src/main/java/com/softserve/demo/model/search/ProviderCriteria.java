package com.softserve.demo.model.search;

import com.softserve.demo.model.Service;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class ProviderCriteria {

    private String city;
    private String region;
    private String country;
    private int minRating;
    private String sortBy;
    private List<Service> checkedServices;

    public void setMinRating(String minRating) {
        if (minRating == null) {
            this.minRating = 0;
        } else {
            this.minRating = Integer.valueOf(minRating);
        }
    }

    public void setSortBy(String sortBy) {
        if (sortBy == null) {
            this.sortBy = "registrationDate";
        } else {
            this.sortBy = sortBy;
        }
    }
}
