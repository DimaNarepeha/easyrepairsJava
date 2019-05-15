package com.softserve.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class ServiceProvider {
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



    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "provider",cascade = CascadeType.REMOVE)
    private List<Order> orders;

    @ManyToMany(cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinTable(
            name = "provider_service",
            joinColumns = {@JoinColumn(name = "provider_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")}
    )
    private List<Service> services = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

}
