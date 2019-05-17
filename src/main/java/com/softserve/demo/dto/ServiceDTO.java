package com.softserve.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ServiceDTO {
    private Integer id;
    private String serviceName;
    private List<OfferDTO> offersDTO;
    private List<ProviderDTO> providersDTO;
}
