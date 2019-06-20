package com.softserve.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "info_for_post")
public class PostInfo {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @Column(name = "description")
    private String description;

    @Column(name = "photo")
    private String photo;
}