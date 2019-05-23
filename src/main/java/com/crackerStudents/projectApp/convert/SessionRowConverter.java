package com.crackerStudents.projectApp.convert;

import com.crackerStudents.projectApp.DTO.SessionDTO;
import com.crackerStudents.projectApp.domain.SessionRow;

import java.util.Date;

public class SessionRowConverter {

    public static SessionRow DTOtoEntity(SessionDTO sessionDTO){
        SessionRow sessionRow = new SessionRow();
        sessionRow.setAnswered(new Date());
        sessionRow.setAnswer(sessionDTO.getReply());
        sessionRow.setCardId(sessionDTO.getCard_id());
        return sessionRow;
    }
}
