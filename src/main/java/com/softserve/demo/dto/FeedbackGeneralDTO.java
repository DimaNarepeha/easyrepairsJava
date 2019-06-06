package com.softserve.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class FeedbackGeneralDTO {
    private Integer id;
    private String comment;
    private Double rating;
    private String username;
    private String image;
}
