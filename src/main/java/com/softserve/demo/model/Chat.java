package com.softserve.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Entity
@Table(name = "chat")
public class Chat {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "message_from")
    private Customer messageFrom;
    @ManyToOne
    @JoinColumn(name = "message_to")
    private Provider messageTo;

    private String message;
}
