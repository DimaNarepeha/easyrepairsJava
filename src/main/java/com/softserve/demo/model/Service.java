package com.softserve.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "services")
public class Service {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "service_name")
    private String serviceName;

    @ManyToMany(mappedBy = "services",fetch = FetchType.LAZY)
    private List<Offer> offers = new ArrayList<>();

    @JsonBackReference
    @ManyToMany(mappedBy = "services",fetch = FetchType.LAZY)
    private List<Provider> providers = new ArrayList<>();

}
