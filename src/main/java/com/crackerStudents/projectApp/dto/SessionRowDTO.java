package com.crackerStudents.projectApp.dto;



import java.sql.Date;

public class SessionRowDTO {
    private int id;

    private int answer;

    private Date update;

    private CardDTO cardId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public CardDTO getCardId() {
        return cardId;
    }

    public void setCardId(CardDTO cardId) {
        this.cardId = cardId;
    }
}
