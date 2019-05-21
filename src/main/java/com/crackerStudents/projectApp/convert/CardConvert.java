package com.crackerStudents.projectApp.convert;

import com.crackerStudents.projectApp.DTO.CardDTO;
import com.crackerStudents.projectApp.domain.Card;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

/**
 * @Author Krylov Sergey
 */
public class CardConvert {

    private final ModelMapper modelMapper;

    @Autowired
    public CardConvert(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CardDTO convertToDto(Card card) {
        CardDTO cardDTO = modelMapper.map(card, CardDTO.class);
        return cardDTO;
    }


    public Card convertToEntity(CardDTO cardDto) throws ParseException {
        Card card = modelMapper.map(cardDto, Card.class);
        return card;
    }
}
