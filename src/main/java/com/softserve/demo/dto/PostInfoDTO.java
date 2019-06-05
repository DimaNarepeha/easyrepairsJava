package com.softserve.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostInfoDTO {
    private Integer id;
    private String description;
    private String photo;
}
