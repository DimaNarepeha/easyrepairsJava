package com.softserve.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.softserve.demo.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Created by Illia Chenchak
 */
@Getter
@Setter
@ToString
public class FeedbackDTO {

    private Integer id;
    private String comment;
    private Double rating;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
    private UserDTO addressedFrom;
    private UserDTO addressedTo;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;
    private String userTo;
    private String userFrom;

}
