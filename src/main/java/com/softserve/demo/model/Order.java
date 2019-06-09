package com.softserve.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "orders")
public class Order {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "extra_details")
    private String extraDetails;

    @Column(name = "price")
    private String price;

    @CreationTimestamp
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "time_requirement")
    private String timeRequirement;

    @Column(name = "customer_approved")
    private String customerApproved;

    @Column(name = "provider_approved")
    private String providerApproved;

    @JsonManagedReference(value = "order_provider")
    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @JsonManagedReference(value = "order_customer")
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonManagedReference(value="location-order")
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonManagedReference(value = "service_order")
    @JoinTable(
            name = "order_service",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")}
    )
    private List<Service> services;
}
