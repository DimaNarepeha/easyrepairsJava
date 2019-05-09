package com.softserve.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "customer")
public class CustomerEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "path_to_photo")
    private String image;

    @Column(name = "last_update")
    private Date updated;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer",cascade = CascadeType.REMOVE)
    private List<Offer> offers;



    @Column(name = "usr")
    private int userId;

}
