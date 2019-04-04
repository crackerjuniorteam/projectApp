package com.crackerStudents.projectApp.dao;

import com.crackerStudents.projectApp.domain.Card;
import com.crackerStudents.projectApp.domain.Pack;
import com.crackerStudents.projectApp.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PackRepository extends JpaRepository<Pack, Integer> {
    //Set<Pack> findAllByCardsIn(List<Card> cards);
    Set<Pack> findAllByTagsIn(List<Tag> tags);
}
