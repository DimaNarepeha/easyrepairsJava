package com.softserve.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "notifications")
public class Notification {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "notification_header")
    private String header;
    @Column(name = "notification_message")
    private String message;
    @Column(name = "created_at")
    private LocalDateTime time;
    @Column(name = "seen")
    private Boolean seen; // if user have already read this notification
}
