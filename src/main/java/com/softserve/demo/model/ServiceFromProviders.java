package com.softserve.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="services")
public class Service {

    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="service_name", length = 200)
    private String serviceName;
}
