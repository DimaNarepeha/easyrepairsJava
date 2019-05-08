package com.softserve.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;


@Data
@Entity
@Table(name="customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="firstname", length = 45, nullable = false)
    private String firstName;

    @Column(name ="lastname", length = 45, nullable = false )
    private String lastName;

    @Column(length = 60)
    private String email;

    @Column(name="user_id")
    private int userId;

    @Column(name="path_to_photo",length = 400)
    private String image;

    @Column(name="last_update")
    private Date updated;
}
