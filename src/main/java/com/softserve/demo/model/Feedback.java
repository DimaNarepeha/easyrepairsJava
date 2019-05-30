package com.softserve.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
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

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "addressed_from")
    private User addressedFrom;

    @ManyToOne
    @JoinColumn(name = "addressed_to")
    private User addressedTo;


    @CreationTimestamp
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "available")
    private Boolean availabel;
}
