package com.softserve.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NotificationDTO {
    private Integer id;
    private String header;
    private String message;
    private String time;
    private Boolean seen;
}
