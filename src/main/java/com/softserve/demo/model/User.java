package com.softserve.demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usr")
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "login")
    private String username;
    private String password;
    private String image;
    private String email;
    @Column(name = "is_activated")
    private boolean isActivated;
    @Column(name = "activation_code")
    private String activationCode;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    @Column(name = "attempts")
    private Integer attempts;
    @Column(name = "last_fail")
    private LocalDateTime lastFail;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "addressedFrom")
    private List<Feedback> feedbackFrom;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "addressedTo", cascade = CascadeType.REMOVE)
    private List<Feedback> feedbackTo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Phone> phones;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable
    private List<Notification> notifications;
}
