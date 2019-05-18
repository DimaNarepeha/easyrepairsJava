package com.softserve.demo.dto;

import com.softserve.demo.model.Service;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class ServiceProviderInfoDTO {
    private Integer id;
    private String name;
    private String description;
    private String image;
    private Date registrationDate;
    private List<String> services = new ArrayList<>();
    private String  city;
    private double raiting;
    private long countComment;

    public ServiceProviderInfoDTO(){}

}
