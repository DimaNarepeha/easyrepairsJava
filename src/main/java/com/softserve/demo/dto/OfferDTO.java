package com.softserve.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OfferDTO {

    private Long id;
    private Integer customerId;
    private Integer providerId;
    private Date StartDate;
    private String description;
    private Integer locationId;
}
