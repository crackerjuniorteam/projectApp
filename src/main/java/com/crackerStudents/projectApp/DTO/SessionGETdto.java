package com.crackerStudents.projectApp.DTO;

import java.util.UUID;

public class SessionGETdto {
    private String question;
    private String answer;
    private UUID session_id;
    private UUID pack_id;

    public SessionGETdto(String question, String answer, UUID session_id, UUID pack_id) {
        this.question = question;
        this.answer = answer;
        this.session_id = session_id;
        this.pack_id = pack_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public UUID getSession_id() {
        return session_id;
    }

    public void setSession_id(UUID session_id) {
        this.session_id = session_id;
    }

    public UUID getPack_id() {
        return pack_id;
    }

    public void setPack_id(UUID pack_id) {
        this.pack_id = pack_id;
    }
}
