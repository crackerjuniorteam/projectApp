package com.crackerStudents.projectApp.convert;

import com.crackerStudents.projectApp.DTO.CardDTO;
import com.crackerStudents.projectApp.domain.Card;
import com.crackerStudents.projectApp.domain.User;
import org.junit.Test;
import org.junit.Before;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/**
 * @Author Krylov Sergey
 */
public class CardTest {
    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenEntityCardConvertToCardDTO() {
        Card card = new Card("Кто ты?", "Разработчик", new User("Вася"));
        CardDTO cardDTO = modelMapper.map(card, CardDTO.class);
        assertEquals(card.getQuestion(), cardDTO.getQuestion());
        assertEquals(card.getAnswer(), cardDTO.getAnswer());
        assertEquals(card.getAuthor(), cardDTO.getAuthor());
    }

    @Test
    public void whenCardDTOConvertToCardEntity() {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setQuestion("Кто ты?");
        cardDTO.setAnswer("Разработчик");
        cardDTO.setAuthor(new User("Вася"));

        Card card = modelMapper.map(cardDTO, Card.class);
        assertEquals(card.getQuestion(), cardDTO.getQuestion());
        assertEquals(card.getAnswer(), cardDTO.getAnswer());
        assertEquals(card.getAuthor(), cardDTO.getAuthor());
    }
}
