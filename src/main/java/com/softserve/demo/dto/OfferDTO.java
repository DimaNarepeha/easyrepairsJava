package com.softserve.demo.dto;

import com.softserve.demo.model.Customer;
import com.softserve.demo.model.Location;
import com.softserve.demo.model.Service;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.sql.Date;

@Getter
@Setter
@ToString
public class OfferDTO {
    private Integer id;
    private String description;
    private Date startDate;
    private Customer customer;
    private Location location;
    private List<Service> services;
}
