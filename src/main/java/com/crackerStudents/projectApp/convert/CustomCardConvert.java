package com.crackerStudents.projectApp.convert;

import com.crackerStudents.projectApp.DTO.CardDTO;
import com.crackerStudents.projectApp.domain.Card;

public class CustomCardConvert {
    public CardDTO entityToDto(Card cardEntity){
        CardDTO cardDTO = new CardDTO();
        cardDTO.setAnswer(cardEntity.getAnswer());
        cardDTO.setQuestion(cardEntity.getQuestion());
        cardDTO.setId(cardEntity.getId());
        cardDTO.setConsecutive_correct_answer(cardEntity.getConsecutive_correct_answer());
        cardDTO.setAuthor(cardEntity.getAuthor());
        cardDTO.setNext_practice_time(cardEntity.getNext_practice_time());
        return cardDTO;
    }

    public Card DTOtoEntity(CardDTO cardDTO){
        Card card = new Card();
        card.setAnswer(cardDTO.getAnswer());
        card.setQuestion(cardDTO.getQuestion());
        card.setId(cardDTO.getId());
        card.setNext_practice_time(cardDTO.getNext_practice_time());
        card.setConsecutive_correct_answer(cardDTO.getConsecutive_correct_answer());
        return card;
    }

}
