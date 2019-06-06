package com.softserve.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProviderDTO {
    private Integer id;
    private String name;
    @Email
    private String email;
    private String description;
    private String image;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registrationDate;
    private UserDTO userDTO;
    private List<OrderDTO> ordersDTO;
    private List<ServiceDTO> servicesDTO;
    private LocationDTO location;
    private double raiting;
}
