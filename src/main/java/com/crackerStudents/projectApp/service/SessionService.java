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
import java.util.*;

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
    public PackDTO getPackDTOByName(String packName){
        return modelMapper.map(packRepo.findByName(packName),PackDTO.class);
    }

    @Transactional
    public List<CardDTO> getDTOCardsFromPack(UUID packId){
        List<Card> cards = new ArrayList<>(packRepo.findById(packId).orElse(null).getCards());
        CustomCardConvert converter = new CustomCardConvert();
        List<CardDTO> cardDTOS = new ArrayList<>();
        for (Card card: cards) {
            cardDTOS.add(converter.entityToDto(card));
        }
        return cardDTOS;
    }

    @Transactional
    public boolean userHasAccessToPack(User user, UUID packId){
        for (Pack pack : user.getPacks()){
            if (pack.getId().equals(packId)) return true;
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
    public UUID getActiveSessionForUser(User user){
        Set<Session> user_sessions = sessionRepo.findByUsers(user);
        for (Session session: user_sessions) {
            if (session.getActive()) return session.getId();
        }
        return createSession(user).getId();
    }

    @Transactional
    public void saveSessionRow(SessionRowDTO sessionRowDTO, User user){
        Session session = sessionRepo.findById(getActiveSessionForUser(user)).orElse(null);
        SessionRow sessionRow = SessionRowConverter.DTOtoEntity(sessionRowDTO);
        if (!sessionRowDTO.getIsActive()) session.setActive(false);
        sessionRow.setSession(session);
        session.addRow(sessionRow);
        sessionRepo.save(session);

    }



}
