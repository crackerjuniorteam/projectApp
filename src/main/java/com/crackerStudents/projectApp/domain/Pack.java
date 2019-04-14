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
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "is_public")
    private boolean isPublic;

    @Column(name = "likes")
    private int likes;

    @Column(name = "created")
    private Date created;

    @Column(name = "parent_id")
    private int parentId;


    public Pack(){}

    public Pack(String name, Long authorId, boolean isPublic) {
        this.name = name;
        this.authorId = authorId;
        this.isPublic = isPublic;
    }

    public Pack(String name, Long authorId, boolean isPublic, int likes, Date created) {
        this.name = name;
        this.authorId = authorId;
        this.isPublic = isPublic;
        this.likes = likes;
        this.created = created;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usr_packs",
            joinColumns = @JoinColumn(name = "pack_id"),
            inverseJoinColumns = @JoinColumn(name = "usr_id"))
    private List<User> users = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "tag_to_pack",
            joinColumns = {@JoinColumn(name = "pack_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private List<Tag> tags = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "card_in_pack",
            joinColumns = {@JoinColumn(name = "pack_id")},
            inverseJoinColumns = {@JoinColumn(name = "card_id")})
    private List<Card> cards = new ArrayList<>();

    //
    public void addUser(User user) {
        users.add(user);
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
