package com.crackerStudents.projectApp.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "session_row")
public class SessionRow {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Basic
    @Column(name = "card_id")
    private int cardId;

    @Basic
    @Column(name = "answer")
    private int answer;

    @Basic
    @Column(name = "answered")
    private Date answered;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;
}