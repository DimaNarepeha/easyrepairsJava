package com.softserve.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProviderOrderDTO {
    private Integer id;
    private String name;
    private String description;
    private UserDTO userDTO;
}
