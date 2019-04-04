package com.crackerStudents.projectApp.dto;

import lombok.Data;

@Data
public class CardDTO {
    private int id;

    private String question;

    private String answer;

    private PackDTO pack;
}
