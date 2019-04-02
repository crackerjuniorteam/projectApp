package com.crackerStudents.projectApp.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "session", schema = "public")
@Data
public class Session {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id = 0;

    @OneToOne
    @JoinColumn
    private SessionRow row_id;


    @ManyToMany
    @JoinTable(name = "user_session",
            joinColumns = {@JoinColumn(name = "session_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> users = new ArrayList<>();
}

