package com.softserve.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
public class ServiceProviderInfoDTO {
    private Integer id;
    private String name;
    private String description;
    private String image;
    private LocalDateTime registrationDate;
    private List<String> services = new ArrayList<>();
    private String city;
    private double raiting;
    private long countComment;

    public ServiceProviderInfoDTO() {
    }

}
