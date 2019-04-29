package com.crackerStudents.projectApp.convert;

import com.crackerStudents.projectApp.DTO.CardDTO;
import com.crackerStudents.projectApp.domain.Card;

public class CustomCardConvert {
    public CardDTO entityToDto(Card cardEntity){
        CardDTO cardDTO = new CardDTO();
        cardDTO.setAnswer(cardEntity.getAnswer());
        cardDTO.setQuestion(cardEntity.getQuestion());
        cardDTO.setId(cardEntity.getId());
        //cardDTO.setAuthor(cardEntity.getAuthor());
        //cardDTO.setPacks(cardEntity.getPacks());
        return cardDTO;
    }

    public Card DTOtoEntity(CardDTO cardDTO){
        Card card = new Card();
        card.setAnswer(cardDTO.getAnswer());
        card.setQuestion(cardDTO.getQuestion());
        card.setId(cardDTO.getId());
        //card.setAuthor(cardDTO.getAuthor());
        //card.setPacks(cardDTO.getPacks());
        return card;
    }

}
