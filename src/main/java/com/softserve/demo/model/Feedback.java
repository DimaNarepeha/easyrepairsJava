package com.softserve.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

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

    @Column(name = "created_date")
    private Date createdDate;

//    @JoinColumn(name = "addressed_from")
@Column(name = "addressed_from")
    private Integer addressedFrom;

   /// @ManyToOne
   // @JoinColumn(name = "addressed_to")
   @Column(name = "addressed_to")
    private Integer addressedTo;
}
