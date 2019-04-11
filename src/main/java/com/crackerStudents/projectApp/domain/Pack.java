package com.crackerStudents.projectApp.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pack")
public class Pack {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;


    @Column(name = "author_id")
    private int authorId;

    @Column(name = "is_public")
    private boolean isPublic;

    @Column(name = "likes")
    private int likes;

    @Column(name = "created")
    private Date created;

    @Column(name = "parent_id")
    private int parentId;

    @ManyToMany(mappedBy = "packs")
    private List<User> users = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "card_in_pack",
            joinColumns = @JoinColumn(name = "pack_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id"))
    private List<Card> cards = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "tag_to_pack",
            joinColumns = @JoinColumn(name = "pack_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<>();
}
