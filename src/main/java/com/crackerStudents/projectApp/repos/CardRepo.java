package com.crackerStudents.projectApp.repos;

import com.crackerStudents.projectApp.domain.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CardRepo extends CrudRepository<Card, Integer> {
    List<Card> findByAnswer(String answer);
}
