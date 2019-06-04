package com.softserve.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "addressed_from")
    private User addressedFrom;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "addressed_to")
    private User addressedTo;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "available")
    private Boolean availabel;

    @Column(name = "user_to")
    private String userTo;

    @Column(name = "user_from")
    private String userFrom;
}
