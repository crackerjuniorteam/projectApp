package com.crackerStudents.projectApp.dto;

import lombok.Data;

@Data
public class UserDTO {
    private int id;

    private String login;

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private PackDTO pack;

    private SessionDTO session;
}
