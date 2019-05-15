package com.crackerStudents.projectApp.service;


import com.crackerStudents.projectApp.DTO.CardDTO;
import com.crackerStudents.projectApp.DTO.PackDTO;
import com.crackerStudents.projectApp.DTO.SessionRowDTO;
import com.crackerStudents.projectApp.convert.CustomCardConvert;
import com.crackerStudents.projectApp.convert.SessionRowConverter;
import com.crackerStudents.projectApp.domain.*;
import com.crackerStudents.projectApp.repos.PackRepo;
import com.crackerStudents.projectApp.repos.SessionRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    @Transactional
    public Session createSession(User user){
        Session session = new Session();
        session.setActive(true);
        session.setStartTime(new Date());
        session.setUsers(user);
        return sessionRepo.save(session);
    }

    /**
     * Searches for active sessions for user, if not found creates and returns one
     * @param user
     * @return Session entity object, with active status.
     */
    @Transactional
    public Session getActiveSessionForUser(User user){
        Set<Session> user_sessions = sessionRepo.findByUsers(user);
        for (Session session: user_sessions) {
            if (session.getActive()) return session;
        }
        return createSession(user);
    }

    @Transactional
    public void saveSessionRow(SessionRowDTO sessionRowDTO, User user){
        System.out.println("sessionRowDTO");
        Session session = getActiveSessionForUser(user);
        SessionRow sessionRow = SessionRowConverter.DTOtoEntity(sessionRowDTO);
        sessionRow.setSession(session);
        session.addRow(sessionRow);
        sessionRepo.save(session);

    }



}
