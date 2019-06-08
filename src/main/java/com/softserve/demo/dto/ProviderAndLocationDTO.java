package com.softserve.demo.dto;

import lombok.Data;

@Data
public class ProviderAndLocationDTO {
    private Integer idProvider;
    private String name;
    private String email;
    private String description;
    private Integer idLocation;
    private String country;
    private String region;
    private String city;

}
