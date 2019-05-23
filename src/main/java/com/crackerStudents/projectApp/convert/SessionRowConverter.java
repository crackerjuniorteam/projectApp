package com.crackerStudents.projectApp.convert;

import com.crackerStudents.projectApp.DTO.SessionGETdto;
import com.crackerStudents.projectApp.domain.SessionRow;

import java.util.Date;

public class SessionRowConverter {

    public static SessionRow DTOtoEntity(SessionGETdto sessionGETdto){
        SessionRow sessionRow = new SessionRow();
        sessionRow.setAnswer(sessionGETdto.getReply());
        sessionRow.setAnswered(new Date());
        sessionRow.setCardId(sessionGETdto.getCard_id());
        return sessionRow;
    }
}
