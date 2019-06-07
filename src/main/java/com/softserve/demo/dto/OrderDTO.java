package com.softserve.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class OrderDTO {
    private Integer id;
    private String description;
    private String extraDetails;
    private String price;
    private String timeRequirement;
    private String customerApproved;
    private String providerApproved;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @NotNull
    private ProviderDTO providerDTO;
    @NotNull
    private CustomerDTO customerDTO;
    @NotNull
    private LocationDTO locationDTO;
    @NotNull
    private List<ServiceDTO> serviceDTOs;
}
