package com.softserve.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDTO {
    String to;
    String subject;
    String text;
}
