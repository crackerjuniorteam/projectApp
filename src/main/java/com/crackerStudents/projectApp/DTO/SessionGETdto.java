package com.crackerStudents.projectApp.DTO;

import java.util.UUID;

public class SessionGETdto {
    private String question;
    private String answer;
    private UUID session_id;
    private UUID pack_id;
    private UUID card_id;
    private int reply;
    private boolean isActive;

    public SessionGETdto(String question, String answer, UUID session_id, UUID pack_id, UUID card_id, boolean isActive) {
        this.question = question;
        this.answer = answer;
        this.session_id = session_id;
        this.pack_id = pack_id;
        this.card_id = card_id;
        this.isActive = isActive;
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

    public int getReply() {
        return reply;
    }

    public void setReply(int reply) {
        this.reply = reply;
    }

    public UUID getCard_id() {
        return card_id;
    }

    public void setCard_id(UUID card_id) {
        this.card_id = card_id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
