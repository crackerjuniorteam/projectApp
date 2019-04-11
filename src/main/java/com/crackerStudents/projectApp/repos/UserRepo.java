package com.crackerStudents.projectApp.repos;


import com.crackerStudents.projectApp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByLogin(String username);
}
