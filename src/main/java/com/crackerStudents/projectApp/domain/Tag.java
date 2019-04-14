package com.crackerStudents.projectApp.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "tag")
    private String tag;

    @ManyToMany
    @JoinTable(name = "tag_to_pack",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "pack_id"))
    private List<Pack> packs = new ArrayList<>();
}