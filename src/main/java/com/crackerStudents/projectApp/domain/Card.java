package com.crackerStudents.projectApp.domain;

import com.crackerStudents.projectApp.convert.JSONview;
import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @JsonIgnore
    @JsonProperty(value = "CardId")
    private UUID id;

    @NotBlank(message = "Please fill the question")
    @Column(name = "question")
    @JsonView(JSONview.QuestionAndAnswer.class)
    private String question;

    @NotBlank(message = "Please fill the answer")
    @Column(name = "answer")
    @JsonView(JSONview.QuestionAndAnswer.class)
    private String answer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToMany(mappedBy = "cards")
    private Set<Pack> packs = new HashSet<>();

    private Date next_practice_time;
    private int consecutive_correct_answer;
    private Date last_time_easy;

    public Card() {
    }

    public Card(String question, String answer, User user) {
        this.question = question;
        this.answer = answer;
        this.author = user;
    }

    public void increment_consecutive_correct_answer(){
        this.consecutive_correct_answer += 1;
    }

    public Set<Pack> getPacks() {
        return packs;
    }

    public void setPacks(Set<Pack> packs) {
        this.packs = packs;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

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
