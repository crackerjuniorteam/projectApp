package com.crackerStudents.projectApp.service;

import com.crackerStudents.projectApp.domain.Card;
import com.crackerStudents.projectApp.repos.CardRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Krylov Sergey
 */
@Service
public class CardService {

    private final CardRepo cardRepo;
    @Autowired
    public CardService(ModelMapper modelMapper, CardRepo cardRepo) {
        this.cardRepo = cardRepo;
    }

    public void updateCard(Card card, String answer, String question){
        card.setAnswer(answer);
        card.setQuestion(question);
        cardRepo.save(card);
    }
}