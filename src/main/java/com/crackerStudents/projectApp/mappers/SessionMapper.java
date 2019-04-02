package com.crackerStudents.projectApp.mappers;

import com.crackerStudents.projectApp.domain.Session;
import com.crackerStudents.projectApp.dto.SessionDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SessionMapper {
    SessionDTO sessionToSessionDto(Session session);

    List<SessionDTO> sessionsToSessionsDto(List<Session> sessions);

    Session sessionDtoToSession(SessionDTO session);

    List<Session> sessionsDtoToSessions(List<SessionDTO> sessions);

}
