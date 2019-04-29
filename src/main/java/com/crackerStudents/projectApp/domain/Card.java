package com.crackerStudents.projectApp.domain;

import com.crackerStudents.projectApp.convert.JSONview;
import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
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

    public Card() {
    }

    public Card(String question, String answer, User user) {
        this.question = question;
        this.answer = answer;
        this.author = user;
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

}
