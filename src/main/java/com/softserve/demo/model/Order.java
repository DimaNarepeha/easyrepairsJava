package com.softserve.demo.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "orders")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    @JsonBackReference(value="order-movement")
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "offer_id", referencedColumnName = "id")
    private Offer offer;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;
}
