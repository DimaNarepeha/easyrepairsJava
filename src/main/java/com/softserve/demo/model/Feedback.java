package com.softserve.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Calendar;

@Getter
@Setter
@Entity
@ToString
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Calendar createdDate;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "addressed_from")
    private User addressedFrom;

    @Column(name = "addressed_to")
    private Integer addressedTo;
}
