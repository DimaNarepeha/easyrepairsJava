package com.softserve.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
public class ProviderOrderDTO {
    private Integer id;
    private String name;
    private String description;
    @NotNull
    private UserDTO userDTO;
    private List<ServiceDTO> serviceDTOs;
}
