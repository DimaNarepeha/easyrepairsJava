package com.softserve.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatDTO {
    private Integer id;
    private  Integer customerId;
    private Integer providerId;
    private  String message;
    private Integer sentBy;
    private  UserDTO messageTo;
    private UserDTO messageFrom;
    private Boolean isRead;
}

