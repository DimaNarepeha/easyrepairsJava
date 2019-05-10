package com.softserve.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "phone")
public class Phone {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "phone_type")
    private String phoneType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
