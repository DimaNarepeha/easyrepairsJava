package com.softserve.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String image;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updated;
    private UserDTO userDTO;
}
