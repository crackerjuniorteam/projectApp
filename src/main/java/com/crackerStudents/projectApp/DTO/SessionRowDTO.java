package com.crackerStudents.projectApp.DTO;

import java.util.Date;
import java.util.UUID;

public class SessionRowDTO {


    private UUID id;
    private int answer;
    private Date answered;
    private boolean isActive;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public Date getAnswered() {
        return answered;
    }

    public void setAnswered(Date answered) {
        this.answered = answered;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
