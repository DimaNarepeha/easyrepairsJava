package com.softserve.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class NotificationDTO {
    private Integer id;
    private String header;
    private String message;
    private LocalDateTime time;
    private Boolean seen;
}
