package com.softserve.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class OfferDTO {
    private Integer id;
    private String description;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    @NotNull
    private CustomerDTO customerDTO;
    @NotNull
    private LocationDTO locationDTO;
    @NotNull
    private List<ServiceDTO> serviceDTOs;
}
