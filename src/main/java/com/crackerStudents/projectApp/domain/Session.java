package com.crackerStudents.projectApp.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;


    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private Set<SessionRow> row_id;



    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User users;

    private Date startTime;
    private Date finishTime;
    private Boolean isActive;
    private UUID pack_id;
    private UUID last_card_id;


    public void addRow(SessionRow sessionRow){
        this.row_id.add(sessionRow);
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Set<SessionRow> getRow_id() {
        return row_id;
    }

    public void setRow_id(Set<SessionRow> row_id) {
        this.row_id = row_id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public UUID getPack_id() {
        return pack_id;
    }

    public void setPack_id(UUID pack_id) {
        this.pack_id = pack_id;
    }

    public UUID getLast_card_id() {
        return last_card_id;
    }

    public void setLast_card_id(UUID last_card_id) {
        this.last_card_id = last_card_id;
    }
}
