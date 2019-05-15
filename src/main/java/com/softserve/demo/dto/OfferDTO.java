package com.softserve.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class OfferDTO {
    private Long id;
    private Date startDate;
    private String description;
//    private CustomerDTO customerDTO;
    private LocationDTO locationDTO;
    private List<String> services;
}
