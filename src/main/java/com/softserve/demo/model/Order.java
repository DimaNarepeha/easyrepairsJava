package com.softserve.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "finish_date")
    private Date finishDate;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "offer_id", referencedColumnName = "id")
    private Offer offer;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "provider_id")
    private ServiceProvider provider;
}
