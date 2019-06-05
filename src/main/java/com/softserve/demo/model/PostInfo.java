package com.softserve.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "info_for_post")
public class PostInfo {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "description")
    private String description;

    @Column(name = "photo")
    private String photo;
}