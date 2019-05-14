package com.crackerStudents.projectApp.RESTcontroller;


import com.crackerStudents.projectApp.DTO.CardDTO;
import com.crackerStudents.projectApp.DTO.SessionRowDTO;
import com.crackerStudents.projectApp.convert.JSONview;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.repos.SessionRepo;
import com.crackerStudents.projectApp.service.SessionService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RESTSessionController {

    private final SessionService sessionService;

    @Autowired
    public RESTSessionController(SessionService sessionService, SessionRepo sessionRepo){
        this.sessionService = sessionService;
    }

    @GetMapping("rest/session/{packName}")
    @JsonView(JSONview.QuestionAndAnswer.class)
    public ResponseEntity<List<CardDTO>> allCards(@PathVariable String packName, @AuthenticationPrincipal User user) {
        sessionService.createSession(user);
        return new ResponseEntity<>(sessionService.getDTOCardsFromPack(packName), HttpStatus.OK);
    }

    @PostMapping("rest/session/{packName}")
    public void saveSessionStats(@PathVariable String packName,
                                 @RequestBody SessionRowDTO sessionRowDTO,
                                 @AuthenticationPrincipal User user)
    {

    }

}