package com.softserve.demo.dto;

import com.softserve.demo.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.sql.Date;
@Getter
@Setter
public class CustomerDTO {

    private Integer id;


    private String firstName;


    private String lastName;


    private String email;


    private String image;


    private Date updated;

    private Integer userId;

}
