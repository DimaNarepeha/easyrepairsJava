package com.softserve.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "post")
public class Post {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "portfolio_id", referencedColumnName = "id")
    private Portfolio portfolio;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "header")
    private String header;

    @Column(name = "main_description")
    private String mainDescription;

    @Column(name = "main_photo")
    private String mainPhoto;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<PostInfo> postInfo;
}
