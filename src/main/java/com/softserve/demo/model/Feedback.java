package com.softserve.demo.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
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

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @JsonBackReference(value = "a_f")
    @ManyToOne
    @JoinColumn(name = "addressed_from")
    private User addressedFrom;

    @JsonBackReference(value = "a_t")
    @ManyToOne
    @JoinColumn(name = "addressed_to")
    private User addressedTo;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

}
