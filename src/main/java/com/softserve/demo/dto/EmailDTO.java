package com.softserve.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EmailDTO {
    @Email
    String addressedTo;
    String subject;
    @NotNull
    String text;
}
