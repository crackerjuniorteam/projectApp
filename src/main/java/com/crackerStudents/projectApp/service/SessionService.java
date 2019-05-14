package com.crackerStudents.projectApp.service;


import com.crackerStudents.projectApp.DTO.CardDTO;
import com.crackerStudents.projectApp.DTO.PackDTO;
import com.crackerStudents.projectApp.convert.CustomCardConvert;
import com.crackerStudents.projectApp.domain.Card;
import com.crackerStudents.projectApp.domain.Pack;
import com.crackerStudents.projectApp.domain.Session;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.repos.PackRepo;
import com.crackerStudents.projectApp.repos.SessionRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SessionService {

    private final PackRepo packRepo;
    private final ModelMapper modelMapper;
    private final SessionRepo sessionRepo;

    @Autowired
    public SessionService(PackRepo packRepo, ModelMapper modelMapper, SessionRepo sessionRepo){
        this.packRepo = packRepo;
        this.modelMapper = modelMapper;
        this.sessionRepo = sessionRepo;
    }

    @Transactional
    public PackDTO getPackByName(String packName){
        return modelMapper.map(packRepo.findByName(packName),PackDTO.class);
    }

    @Transactional
    public List<CardDTO> getDTOCardsFromPack(String packName){
        List<Card> cards = getPackByName(packName).getCards();
        CustomCardConvert converter = new CustomCardConvert();
        List<CardDTO> cardDTOS = new ArrayList<>();
        for (Card card: cards) {
            cardDTOS.add(converter.entityToDto(card));
        }
        //return ObjectMapperUtils.mapAll(cards,CardDTO.class);
        return cardDTOS;
    }

    @Transactional
    public boolean userHasAccessToPack(User user, String packName){
        for (Pack pack : user.getPacks()){
            if (pack.getName().equals(packName)) return true;
        }
        return false;
    }

    public void createSession(User user){
        Session session = new Session();
        session.setActive(true);
        session.setStartTime(new Date());
        session.setUsers(user);
        sessionRepo.save(session);
    }

    //public void createSessionRow()

}
