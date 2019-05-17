package com.softserve.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@Table(name = "service_provider")
public class Provider {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @Column(name = "path_to_photo")
    private String image;

    @Column(name = "last_update")
//    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;



    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "provider",cascade = CascadeType.REMOVE)
    private List<Order> orders;

    @ManyToMany(cascade = CascadeType.REMOVE ,fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinTable(
            name = "provider_service",
            joinColumns = {@JoinColumn(name = "provider_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")}
    )
    private List<Service> services = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;

}
