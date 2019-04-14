package com.crackerStudents.projectApp.repos;

import com.crackerStudents.projectApp.domain.Pack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackRepo extends JpaRepository<Pack, Long> {
    Pack findByName(String name);
}
