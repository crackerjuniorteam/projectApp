package com.crackerStudents.projectApp.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class SessionRowDTO {
    private int id;

    private int cardId;

    private int answer;

    private Date update;
}
