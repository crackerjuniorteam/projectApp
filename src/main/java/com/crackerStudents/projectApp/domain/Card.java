package com.crackerStudents.projectApp.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "card", schema = "public")
@Data
public class Card {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "question")
    private String question;

    @Basic
    @Column(name = "answer")
    private String answer;

    @ManyToMany
    @JoinTable(name = "card_in_pack",
            joinColumns = {@JoinColumn(name = "pack_id")},
            inverseJoinColumns = {@JoinColumn(name = "card_id")})
    private List<Pack> packs = new ArrayList<>();
}
