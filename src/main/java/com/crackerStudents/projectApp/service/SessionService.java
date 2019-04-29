package com.crackerStudents.projectApp.service;


import com.crackerStudents.projectApp.DTO.CardDTO;
import com.crackerStudents.projectApp.DTO.PackDTO;
import com.crackerStudents.projectApp.convert.CustomCardConvert;
import com.crackerStudents.projectApp.domain.Card;
import com.crackerStudents.projectApp.repos.CardRepo;
import com.crackerStudents.projectApp.repos.PackRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SessionService {

    @Autowired
    private CardRepo cardRepo;

    @Autowired
    private PackRepo packRepo;

    @Autowired
    private ModelMapper modelMapper;

    public PackDTO getPackByName(String packName){
        return modelMapper.map(packRepo.findByName(packName),PackDTO.class);
    }

    public List<CardDTO> getDTOCardsFromPack(String packName){
        List<Card> cards = getPackByName(packName).getCards();
        CustomCardConvert conv = new CustomCardConvert();
        List<CardDTO> cardDTOS = new ArrayList<>();
        for (Card card: cards) {
            cardDTOS.add(conv.entityToDto(card));
        }
        //return ObjectMapperUtils.mapAll(cards,CardDTO.class);
        return cardDTOS;
    }

}
