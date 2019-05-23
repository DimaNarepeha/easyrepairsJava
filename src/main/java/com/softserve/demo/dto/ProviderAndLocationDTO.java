package com.softserve.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Illia Chenchak
 */
@Getter
@Setter
@ToString
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
