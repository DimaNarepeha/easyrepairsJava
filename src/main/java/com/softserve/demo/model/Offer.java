package com.softserve.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@Table(name = "offers")
public class Offer {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @UpdateTimestamp
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "description")
    private String description;

    @JsonBackReference(value="customer-movement")
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonBackReference(value="location-movement")
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @JsonBackReference(value="order-movement")
    @OneToOne(mappedBy = "offer")
    private Order order;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinTable(
            name = "offer_service",
            joinColumns = {@JoinColumn(name = "offer_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")}
    )
    private List<Service> services = new ArrayList<>();

}
