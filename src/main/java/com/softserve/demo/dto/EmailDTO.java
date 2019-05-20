package com.softserve.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;

@Getter
@Setter
public class EmailDTO {
    @Email
    String addressedTo;
    @Max(30)
    String subject;
    String text;
}
