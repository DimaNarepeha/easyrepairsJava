package com.softserve.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class OrderDTO {
    private Integer id;
    private Date finishDate;
    private String description;
    private Double price;
    private OfferDTO offerDTO;
    private ProviderDTO providerDTO;
}
