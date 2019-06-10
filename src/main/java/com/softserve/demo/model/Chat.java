package com.softserve.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    private String message;

    @Column(name = "sent_by")
    private Integer sentBy;

    @UpdateTimestamp
    @Column(name = "sending_time")
    private LocalDateTime sendingTime;
}
