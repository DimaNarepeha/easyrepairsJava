package com.softserve.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "services")
public class Service {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "service_name")
    private String serviceName;

    @ManyToMany(mappedBy = "services")
    private List<Offer> offers = new ArrayList<>();

    @ManyToMany(mappedBy = "services")
    private List<ServiceProvider> providers = new ArrayList<>();

}
