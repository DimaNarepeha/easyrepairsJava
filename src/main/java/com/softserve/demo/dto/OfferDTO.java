package com.softserve.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.softserve.demo.util.validators.DatesChecks;
import com.softserve.demo.util.validators.StartEndDates;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@DatesChecks
public class OfferDTO implements StartEndDates {

    private Integer id;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate removeDate;
    @FutureOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @FutureOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @NotNull
    private CustomerDTO customerDTO;
    @NotNull
    private LocationDTO locationDTO;
    @NotNull
    @Size(min = 1)
    private List<ServiceDTO> serviceDTOs;
}
