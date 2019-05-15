package com.softserve.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class CustomerDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String image;
    private Date updated;
    private UserDTO userDTO;
}
