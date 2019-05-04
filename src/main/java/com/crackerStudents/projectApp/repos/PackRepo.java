package com.crackerStudents.projectApp.repos;

import com.crackerStudents.projectApp.domain.Pack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PackRepo extends JpaRepository<Pack, UUID> {
    Pack findByName(String name);
    Boolean existsByName(String name);
}
