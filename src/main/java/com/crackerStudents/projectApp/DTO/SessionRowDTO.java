package com.crackerStudents.projectApp.DTO;

import java.util.Date;
import java.util.UUID;

public class SessionRowDTO {


    private UUID id;
    private int answer;
    private Date answered;
    private boolean isActive;
    private UUID session_id;


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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public UUID getSession_id() {
        return session_id;
    }

    public void setSession_id(UUID session_id) {
        this.session_id = session_id;
    }
}
