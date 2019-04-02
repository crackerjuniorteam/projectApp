package com.crackerStudents.projectApp.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class PackDTO {
    private int id;

    private String name;

    private boolean isPublic;

    private int likes;

    private Date created;

    private CardDTO card;

    private TagDTO tag;

    private UserDTO user;
}
