package com.crackerStudents.projectApp.dao;

import com.crackerStudents.projectApp.domain.Session;
import com.crackerStudents.projectApp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface SessionRepository extends JpaRepository<Session, Integer> {
        Set<Session> findAllByUsersIn(List<User> users);
}
