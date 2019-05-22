package com.softserve.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Date;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerDTO {
    private Integer id;
    @Pattern(regexp = "[A-Z][a-z]*")
    private String firstName;
    @Pattern(regexp = "[A-Z][a-z]*")
    private String lastName;
    @Email
    private String email;
    private String image;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updated;
    private UserDTO userDTO;
}
