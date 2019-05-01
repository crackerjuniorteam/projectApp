package com.crackerStudents.projectApp.service;

import com.crackerStudents.projectApp.domain.Card;
import com.crackerStudents.projectApp.domain.Pack;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.repos.CardRepo;
import com.crackerStudents.projectApp.repos.PackRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class PackService {

    @Autowired
    CardRepo cardRepo;
    @Autowired
    PackRepo packRepo;

    @Transactional
    public void CardSave(Card card){
        Hibernate.initialize(cardRepo.save(card));
    }

    @Transactional
    public void PackSave(String packName){
        Hibernate.initialize(packRepo.save(packRepo.findByName(packName)));
    }

    @Transactional
    public Set<Pack> getPacksByUser(User user){
        return user.getPacks();
    }
}
