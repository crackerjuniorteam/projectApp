package com.crackerStudents.projectApp.convert;

import com.crackerStudents.projectApp.DTO.SessionDTO;
import com.crackerStudents.projectApp.domain.SessionRow;

public class SessionRowConverter {

    public static SessionRow DTOtoEntity(SessionDTO sessionDTO){
        SessionRow sessionRow = new SessionRow();
        sessionRow.setAnswer(sessionDTO.getAnswer());
        sessionRow.setAnswered(sessionDTO.getAnswered());
        sessionRow.setCardId(sessionDTO.getId());
        return sessionRow;
    }
}
