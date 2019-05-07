package com.softserve.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="offers")
public class Offer {

    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="customer_id")
    private Integer customerId;

    @Column(name ="provider_id")
    private Integer providerId;

    @Column(name ="start_date")
    @Temporal(value = TemporalType.DATE)
    private Date StartDate;

    @Column(name ="description", length = 1000)
    private String description;
}
