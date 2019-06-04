package com.softserve.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
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

    @JsonBackReference(value="location-movement")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "location", cascade = CascadeType.REMOVE)
    private List<Offer> offers;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "location", cascade = CascadeType.MERGE)
    private List<Provider> providers;
}
