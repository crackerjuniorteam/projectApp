package com.crackerStudents.projectApp.DTO;

import com.crackerStudents.projectApp.domain.Card;
import com.crackerStudents.projectApp.domain.Tag;
import com.crackerStudents.projectApp.domain.User;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Author Krylov Sergey
 */
public class PackDTO {
    private UUID id;
    private String name;
    private UUID authorId;
    private boolean isPublic;
    private int likes;
    private Date created;
    private int parentId;
    private List<User> users;
    private List<Tag> tags;
    private List<Card> cards;


    public void addUser(User user) {
        users.add(user);
    }


    // getters and setters
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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
}
