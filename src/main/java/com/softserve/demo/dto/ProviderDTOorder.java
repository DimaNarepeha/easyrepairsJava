package com.softserve.demo.dto;

import com.softserve.demo.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProviderDTOorder {
    private Integer id;
    private String name;
    private String description;
    private User user;
}
