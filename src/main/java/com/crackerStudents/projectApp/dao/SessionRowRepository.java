package com.crackerStudents.projectApp.dao;

import com.crackerStudents.projectApp.domain.SessionRow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRowRepository  extends JpaRepository<SessionRow, Integer> {
}
