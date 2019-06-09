package com.softserve.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "favourite")
public class Favourite {
    @Column(name = "id_customer")
    @OneToMany
    private int id_customer;

    @Column(name = "id_service_provider")
    @ManyToOne
    private int idServiceProvider;

}
