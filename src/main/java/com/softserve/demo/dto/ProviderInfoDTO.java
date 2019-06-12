package com.softserve.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;
import java.util.List;

@ToString
@Getter
@Setter
public class ProviderInfoDTO {
    private Integer id;
    private String name;
    private String description;
    private String image;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registrationDate;
    private List<String> services;
    private String city;
    private double raiting;
    private long countComment;
    private long countContract;
    public ProviderInfoDTO() {
    }

}
