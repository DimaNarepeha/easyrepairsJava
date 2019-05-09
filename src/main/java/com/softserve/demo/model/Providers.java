package com.softserve.demo.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Illia Chenchak
 */
@Entity
@Table(name = "service_provider")
@Data
public class Providers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @Column(name = "path_to_photo")
    private String image;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "location_id")
    private Integer locationId;

}
