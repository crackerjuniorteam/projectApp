package com.crackerStudents.projectApp.DTO;

import java.util.Date;
import java.util.UUID;

public class StatDTO {
    private UUID session_id;
    private UUID pack_id;
    private boolean isActive;
    private Date last_action_time;
    private Integer cardsToRepeat;

}
