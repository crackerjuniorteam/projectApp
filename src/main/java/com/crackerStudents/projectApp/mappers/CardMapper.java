package com.crackerStudents.projectApp.mappers;

import com.crackerStudents.projectApp.domain.Card;
import com.crackerStudents.projectApp.dto.CardDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {
    CardDTO cardToCardDto(Card card);
    List<CardDTO>cardsToCardsDto(List<Card> cards);
    Card cardDtoToCard(CardDTO card);
    List<Card> cardsDtoToCards (List<CardDTO> cards);
}
