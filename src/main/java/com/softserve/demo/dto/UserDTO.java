package com.softserve.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    @NotNull
    private Integer id;
    @NotNull
    private String username;
    private String password;
    @NotNull
    private String email;
    private String image;
}
