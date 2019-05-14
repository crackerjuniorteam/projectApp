package com.crackerStudents.projectApp.repos;

import com.crackerStudents.projectApp.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SessionRepo extends JpaRepository<Session, UUID> {

}