package com.softserve.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.omg.CORBA.BAD_POLICY_TYPE;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "location")
public class Location {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "country")
    private String country;

    @Column(name = "region")
    private String region;

    @Column(name = "city")
    private String city;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "location", cascade = CascadeType.REMOVE)
    private List<Offer> offers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "location", cascade = CascadeType.REMOVE)
    private List<ServiceProvider> providers;
}