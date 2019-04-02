package com.crackerStudents.projectApp.dao;

import com.crackerStudents.projectApp.domain.Card;
import com.crackerStudents.projectApp.domain.Pack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CardRepository extends JpaRepository<Card, Integer> {
    Set<Card> findAllByPacksIn(List<Pack> packs);
}
