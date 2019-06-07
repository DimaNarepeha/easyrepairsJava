package com.softserve.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ChatDTO {
    private Integer id;
   private  Integer messageTo;
   private Integer messageFrom;
  private  String message;
    private Integer sentBy;


}
