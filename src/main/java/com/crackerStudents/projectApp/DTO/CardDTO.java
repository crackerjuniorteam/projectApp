package com.crackerStudents.projectApp.DTO;

import com.crackerStudents.projectApp.convert.JSONview;
import com.crackerStudents.projectApp.domain.Pack;
import com.crackerStudents.projectApp.domain.User;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.Date;
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

    private Date next_practice_time;
    private int consecutive_correct_answer;
    private Date last_time_easy;

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

    public Date getNext_practice_time() {
        return next_practice_time;
    }

    public void setNext_practice_time(Date next_practice_time) {
        this.next_practice_time = next_practice_time;
    }

    public int getConsecutive_correct_answer() {
        return consecutive_correct_answer;
    }

    public void setConsecutive_correct_answer(int consecutive_correct_answer) {
        this.consecutive_correct_answer = consecutive_correct_answer;
    }

    public Date getLast_time_easy() {
        return last_time_easy;
    }

    public void setLast_time_easy(Date last_time_easy) {
        this.last_time_easy = last_time_easy;
    }
}
