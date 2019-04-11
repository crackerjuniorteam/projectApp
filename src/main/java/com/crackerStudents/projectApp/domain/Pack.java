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

    @ManyToMany(mappedBy = "packs")
    private List<User> users = new ArrayList<>();

    //
    public void addUser(User user) {
        users.add(user);
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
