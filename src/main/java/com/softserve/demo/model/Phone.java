package com.softserve.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
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

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
