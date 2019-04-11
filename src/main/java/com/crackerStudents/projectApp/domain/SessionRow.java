package com.crackerStudents.projectApp.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "session_row")
public class SessionRow {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "card_id")
    private int cardId;

    @Basic
    @Column(name = "answer")
    private int answer;

    @Basic
    @Column(name = "updated")
    private Date updated;

    @OneToOne(mappedBy = "row_id")
    private Session session;
}