package com.crackerStudents.projectApp.dto;


public class TagDTO {
    private int id;

    private String text;

    private PackDTO pack;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PackDTO getPack() {
        return pack;
    }

    public void setPack(PackDTO pack) {
        this.pack = pack;
    }
}
