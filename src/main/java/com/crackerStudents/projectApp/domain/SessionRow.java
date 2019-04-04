package com.crackerStudents.projectApp.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "session_row", schema = "public")
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
