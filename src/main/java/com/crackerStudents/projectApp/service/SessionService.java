package com.crackerStudents.projectApp.service;


import com.crackerStudents.projectApp.DTO.CardDTO;
import com.crackerStudents.projectApp.DTO.PackDTO;
import com.crackerStudents.projectApp.domain.Card;
import com.crackerStudents.projectApp.repos.CardRepo;
import com.crackerStudents.projectApp.repos.PackRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return cards.stream().map(card -> convertToDto(card)).collect(Collectors.toList());
    }

    private CardDTO convertToDto(Card card) {
        return modelMapper.map(card, CardDTO.class);
    }

}
