package com.crackerStudents.projectApp.dto;


public class CardDTO {
    private int id;

    private String question;

    private String answer;

    private PackDTO pack;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public PackDTO getPack() {
        return pack;
    }

    public void setPack(PackDTO pack) {
        this.pack = pack;
    }
}
