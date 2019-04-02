package com.crackerStudents.projectApp.dto;

import lombok.Data;

@Data
public class CardDTO {
    private int id;

    private String ask;

    private String answer;

    private PackDTO pack;
}
