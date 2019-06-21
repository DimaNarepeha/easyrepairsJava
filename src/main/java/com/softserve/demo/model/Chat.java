package com.softserve.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private String message;

    @CreationTimestamp
    @Column(name = "sending_time")
    private LocalDateTime sendingTime;

    @OneToOne
    @JoinColumn(name = "message_to", referencedColumnName = "id")
    private User messageTo;

    @OneToOne
    @JoinColumn(name = "message_from", referencedColumnName = "id")
    private User messageFrom;

    @Column(name = "is_read", columnDefinition = "boolean default false")
    private Boolean isRead;


}
