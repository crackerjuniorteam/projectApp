package com.crackerStudents.projectApp.dto;



import java.sql.Date;

public class PackDTO {
    private int id;

    private String name;

    private boolean isPublic;

    private int likes;

    private Date created;

    private int parentId;

    private CardDTO card;

    private TagDTO tag;

    private UserDTO user;

    private UserDTO authorId; //зачем мне это?

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public CardDTO getCard() {
        return card;
    }

    public void setCard(CardDTO card) {
        this.card = card;
    }

    public TagDTO getTag() {
        return tag;
    }

    public void setTag(TagDTO tag) {
        this.tag = tag;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public UserDTO getAuthorId() {
        return authorId;
    }

    public void setAuthorId(UserDTO authorId) {
        this.authorId = authorId;
    }
}
