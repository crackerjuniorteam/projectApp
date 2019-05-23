package com.crackerStudents.projectApp.repos;

import com.crackerStudents.projectApp.domain.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface CardRepo extends CrudRepository<Card, UUID> {
    List<Card> findByAnswer(String answer);
    Optional<Card> findById(UUID id);
}
