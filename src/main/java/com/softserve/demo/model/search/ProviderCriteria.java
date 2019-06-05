package com.softserve.demo.model.search;

import com.softserve.demo.model.Service;

import java.util.List;

public class ProviderCriteria {

    private String city;
    private String region;
    private String country;
    private int minRating;
    private String sortBy;
    private List<Service> checkedServices;

    public ProviderCriteria() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String location) {
        this.city = location;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getMinRating() {
        return minRating;
    }

    public void setMinRating(String minRating) {

        if (minRating == null) {
            this.minRating = 0;
        } else {
            this.minRating = Integer.valueOf(minRating);
        }
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        if (sortBy == null) {
            this.sortBy = "registrationDate";
        } else {
            this.sortBy = sortBy;
        }
    }

    public List<Service> getCheckedServices() {
        return checkedServices;
    }

    public void setCheckedServices(List<Service> services) {
        this.checkedServices = services;
    }
}
