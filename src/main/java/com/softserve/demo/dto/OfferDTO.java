package com.softserve.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OfferDTO {

    private Long id;
    private CustomerDTO customerDTO;
    private Date StartDate;
    private String description;
    private Integer locationId;
    private LocationDTO locationDTO;
    private List<ServiceDTO> serviceDTO;

}
