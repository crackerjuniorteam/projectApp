package com.crackerStudents.projectApp.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String question = "";
    private String answer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

  @ManyToMany
  @JoinTable(name = "card_in_pack",
      joinColumns = {@JoinColumn(name = "card_id")},
      inverseJoinColumns = {@JoinColumn(name = "pack_id")})
  private List<Pack> packs = new ArrayList<>();

    public Card() {
    }

    public Card(String question, String answer, User user) {
        this.question = question;
        this.answer = answer;
        this.author = user;
    }

    public String getAuthorName() {
        return author != null ? author.getLogin() : "<none>";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
