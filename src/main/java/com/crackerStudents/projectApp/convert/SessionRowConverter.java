package com.crackerStudents.projectApp.convert;

import com.crackerStudents.projectApp.DTO.SessionRowDTO;
import com.crackerStudents.projectApp.domain.SessionRow;

public class SessionRowConverter {

    public static SessionRow DTOtoEntity(SessionRowDTO sessionRowDTO){
        SessionRow sessionRow = new SessionRow();
        sessionRow.setAnswer(sessionRowDTO.getAnswer());
        sessionRow.setAnswered(sessionRowDTO.getAnswered());
        sessionRow.setCardId(sessionRowDTO.getId());
        return sessionRow;
    }
}
