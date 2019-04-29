package com.crackerStudents.projectApp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name = "pack")
public class Pack {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "author_id")
    private UUID authorId;

    @Column(name = "is_public")
    private boolean isPublic;

    @Column(name = "likes")
    private int likes;

    @Column(name = "created")
    private Date created;

    // Зачем нам это поле?
    @Column(name = "parent_id")
    private int parentId;

    @ManyToMany(mappedBy = "packs")
    //@Fetch(value = FetchMode.SUBSELECT)
    private Set<User> users = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "tag_to_pack",
            joinColumns = {@JoinColumn(name = "pack_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "card_in_pack",
            joinColumns = {@JoinColumn(name = "pack_id")},
            inverseJoinColumns = {@JoinColumn(name = "card_id")})
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<Card> cards = new HashSet<>();

    //

    public Pack() {
    }

    public Pack(String name, UUID authorId, boolean isPublic, int likes, Date date) {
        this.name = name;
        this.authorId = authorId;
        this.isPublic = isPublic;
        this.likes = likes;
        this.created = date;
    }


    //
    public void addCard(Card card) {
        cards.add(card);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}
