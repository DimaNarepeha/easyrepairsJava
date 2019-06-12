package com.softserve.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class OrderDTO {

    private Integer id;
    @NotNull
    @Size(min = 2, max = 1000)
    private String description;
    @Size(max = 1000)
    private String extraDetails;
    @Size(max = 1000)
    private String price;
    @NotNull
    @Size(max = 30)
    private String timeRequirement;
    @Size(max = 30)
    private String customerApproved;
    @Size(max = 30)
    private String providerApproved;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    @FutureOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @FutureOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @NotNull
    private ProviderDTOorder providerDTO;
    @NotNull
    private CustomerDTO customerDTO;
    @NotNull
    private LocationDTO locationDTO;
    @NotNull
    @Size(min = 1)
    private List<ServiceDTO> serviceDTOs;
}
