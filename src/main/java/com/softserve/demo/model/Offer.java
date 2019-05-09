package com.softserve.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "offers")
public class Offer {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToOne(mappedBy = "offer")
    private Order order;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "offer_service",
            joinColumns = {@JoinColumn(name = "offer_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")}
    )
    private List<Service> services = new ArrayList<>();

}
