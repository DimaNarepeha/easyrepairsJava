package com.softserve.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class LocationDTO {
    private Integer id;
    private String country;
    private String region;
    private String city;
}
