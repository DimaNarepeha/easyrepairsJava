package com.softserve.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class PortfolioDTO {
    private Integer id;
    private LocalDateTime lastUpdate;
    private List<PostDTO> posts;
}
