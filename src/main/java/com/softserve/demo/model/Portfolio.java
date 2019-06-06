package com.softserve.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "portfolio")
public class Portfolio {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "provider_id", referencedColumnName = "id")
    private Provider provider;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "portfolio", cascade = CascadeType.REMOVE)
    private List<Post> posts;
}
