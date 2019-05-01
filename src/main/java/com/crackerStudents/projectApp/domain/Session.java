package com.crackerStudents.projectApp.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
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
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @OneToOne
    @JoinColumn
    private SessionRow row_id;


    @ManyToMany(mappedBy = "sessions")
    private Set<User> users = new HashSet<>();

    //


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public SessionRow getRow_id() {
        return row_id;
    }

    public void setRow_id(SessionRow row_id) {
        this.row_id = row_id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
