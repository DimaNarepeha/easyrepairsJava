package com.softserve.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@ToString
public class PostDTO {
    private Integer id;
    private LocalDateTime createdDate;
    private String header;
    private String mainDescription;
    private String mainPhoto;
    private List<PostInfoDTO> postInfo;
}
