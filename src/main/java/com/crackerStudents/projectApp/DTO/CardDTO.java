package com.crackerStudents.projectApp.DTO;

import com.crackerStudents.projectApp.convert.JSONview;
import com.crackerStudents.projectApp.domain.Pack;
import com.crackerStudents.projectApp.domain.User;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;
import java.util.UUID;

/**
 * Author Krylov Sergey
 */

public class CardDTO {

    @JsonView(JSONview.QuestionAndAnswer.class)
    private UUID id;

    @JsonView(JSONview.QuestionAndAnswer.class)
    private String question;

    @JsonView(JSONview.QuestionAndAnswer.class)
    private String answer;
    private User author;
    private List<Pack> packs;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Pack> getPacks() {
        return packs;
    }

    public void setPacks(List<Pack> packs) {
        this.packs = packs;
    }

}
