package com.crackerStudents.projectApp.service;


import com.crackerStudents.projectApp.DTO.CardDTO;
import com.crackerStudents.projectApp.DTO.PackDTO;
import com.crackerStudents.projectApp.DTO.SessionGETdto;
import com.crackerStudents.projectApp.convert.CustomCardConvert;
import com.crackerStudents.projectApp.convert.SessionRowConverter;
import com.crackerStudents.projectApp.domain.*;
import com.crackerStudents.projectApp.repos.CardRepo;
import com.crackerStudents.projectApp.repos.PackRepo;
import com.crackerStudents.projectApp.repos.SessionRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SessionService {

    private final PackRepo packRepo;
    private final ModelMapper modelMapper;
    private final SessionRepo sessionRepo;
    private final CardRepo cardRepo;


    @Autowired
    public SessionService(PackRepo packRepo, ModelMapper modelMapper, SessionRepo sessionRepo, CardRepo cardRepo){
        this.packRepo = packRepo;
        this.modelMapper = modelMapper;
        this.sessionRepo = sessionRepo;
        this.cardRepo = cardRepo;
    }

    @Transactional(readOnly = false)
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
        session.setLast_card_id(UUID.fromString( "00000000-0000-0000-0000-000000000000" ));
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
    public void saveSessionRow(SessionGETdto sessionGETdto, User user){
        Session session = sessionRepo.findById(getActiveSessionForUser(user)).orElse(null);
        SessionRow sessionRow = SessionRowConverter.DTOtoEntity(sessionGETdto);
        if (!sessionGETdto.isActive()) session.setActive(false);
        sessionRow.setSession(session);
        session.addRow(sessionRow);
        sessionRepo.save(session);

    }

    @Transactional
    public CardDTO getNextCard(UUID packId, UUID sessionId){

        final int SUBDECK_SIZE = 15;

        Session session = sessionRepo.findById(sessionId).orElse(null);
        UUID lastCardId = session.getLast_card_id();
        List<CardDTO> cards = getDTOCardsFromPack(packId);
        cards.sort(Comparator.comparing(CardDTO::getNext_practice_time));
        List<CardDTO> subDeck = cards.subList(0, cards.size() > SUBDECK_SIZE ? SUBDECK_SIZE : cards.size());
        int rnd = new Random().nextInt(subDeck.size());
        CardDTO card = subDeck.get(rnd);
        while (card.getId().equals(lastCardId)){
            card = subDeck.get(new Random().nextInt(subDeck.size()));
        }
        session.setLast_card_id(card.getId());
        sessionRepo.save(session);
        return card;
    }

    public void updateCard(SessionGETdto sessionGETdto){

        final int CONSECUTIVE_CORRECT_TO_REMOVE_FROM_SUBDECK_WHEN_KNOWN = 2;
        final int CONSECUTIVE_CORRECT_TO_REMOVE_FROM_SUBDECK_WHEN_WILL_FORGET = 3;
        final int DAYS_TO_NEXT_REVIEW_IF_KNOW_WELL = 2;
        final double REMINDER_RATE = 1.6;

        Card card = cardRepo.findById(sessionGETdto.getCard_id()).orElse(null);
        if (sessionGETdto.getReply() == 1){
            card.increment_consecutive_correct_answer();

            if (card.getConsecutive_correct_answer() >= CONSECUTIVE_CORRECT_TO_REMOVE_FROM_SUBDECK_WHEN_KNOWN){
                long milliseconds_since_last_easy = (new Date().getTime() - card.getLast_time_easy().getTime());
                long milliseconds_to_next_review = Math.round((milliseconds_since_last_easy +
                        (DAYS_TO_NEXT_REVIEW_IF_KNOW_WELL * 1000 * 60 * 60 * 24)) * REMINDER_RATE);
                Date next_practice_time = new Date();
                next_practice_time.setTime(milliseconds_to_next_review + next_practice_time.getTime());
                card.setNext_practice_time(next_practice_time);
                card.setLast_time_easy(new Date());
            }
            else {
                card.setNext_practice_time(new Date());
            }
        }
        else if (sessionGETdto.getReply() == 2){
            card.increment_consecutive_correct_answer();

            if (card.getConsecutive_correct_answer() >= CONSECUTIVE_CORRECT_TO_REMOVE_FROM_SUBDECK_WHEN_WILL_FORGET){
                Date next_practice_time = new Date();
                next_practice_time.setTime(next_practice_time.getTime() + 1000 * 60 * 60 * 24);
                card.setNext_practice_time(next_practice_time);
            }
            else {
                card.setNext_practice_time(new Date());
            }
        }
        else if (sessionGETdto.getReply() == 3){
            card.setConsecutive_correct_answer(0);
            card.setNext_practice_time(new Date());
        }
        cardRepo.save(card);
    }



}
