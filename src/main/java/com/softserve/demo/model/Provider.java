package com.softserve.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
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
    private Date lastUpdate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProviderStatus status;

    @Column(name = "raiting")
    private double raiting;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "provider",cascade = CascadeType.REMOVE)
    private List<Order> orders;

    @ManyToMany(cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(
            name = "provider_service",
            joinColumns = {@JoinColumn(name = "provider_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")}
    )
    private List<Service> services = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

}
