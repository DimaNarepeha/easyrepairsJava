package com.softserve.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostDTO {
    private Integer id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
    private String header;
    private String mainDescription;
    private String mainPhoto;
    private Integer portfolioId;
    private List<PostInfoDTO> postInfo;
    private String username;
}
