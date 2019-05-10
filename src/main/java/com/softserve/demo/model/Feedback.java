package com.softserve.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "feedback")
public class Feedback {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "addressed_from")
    private User addressedFrom;

    @ManyToOne
    @JoinColumn(name = "addressed_to")
    private User addressedTo;
}
