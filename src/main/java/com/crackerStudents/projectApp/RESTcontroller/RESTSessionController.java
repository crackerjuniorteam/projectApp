package com.crackerStudents.projectApp.RESTcontroller;


import com.crackerStudents.projectApp.DTO.CardDTO;
import com.crackerStudents.projectApp.DTO.SessionRowDTO;
import com.crackerStudents.projectApp.convert.JSONview;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.repos.SessionRepo;
import com.crackerStudents.projectApp.service.SessionService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@PreAuthorize("hasAuthority('USER')")
public class RESTSessionController {

    private final SessionService sessionService;

    @Autowired
    public RESTSessionController(SessionService sessionService, SessionRepo sessionRepo){
        this.sessionService = sessionService;
    }

    @GetMapping("rest/session/{packId}")
    @JsonView(JSONview.QuestionAndAnswer.class)
    public ResponseEntity<List<CardDTO>> allCards(@PathVariable UUID packId, @AuthenticationPrincipal User user) {

        if (sessionService.userHasAccessToPack(user, packId)){
            UUID uuid = sessionService.getActiveSessionForUser(user);
            return new ResponseEntity<>(sessionService.getDTOCardsFromPack(packId), HttpStatus.OK);
        }
        else {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("/error"));
            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
        }
    }

    @PostMapping("rest/session/{packId}")
    public void saveSessionStats(@PathVariable UUID packId,
                                 @RequestBody SessionRowDTO sessionRowDTO,
                                 @AuthenticationPrincipal User user)
    {
        if (sessionService.userHasAccessToPack(user, packId)) {
            System.out.println(sessionRowDTO.getAnswer());
            System.out.println(sessionRowDTO.getId());
            System.out.println(sessionRowDTO.getIsActive());
            sessionRowDTO.setAnswered(new Date());
            sessionService.saveSessionRow(sessionRowDTO, user);
        }
    }

    @PostMapping("rest/session/end/{packId}")
    public void endSession(@PathVariable UUID packId,
                                 @RequestBody SessionRowDTO sessionRowDTO,
                                 @AuthenticationPrincipal User user)
    {
        if (sessionService.userHasAccessToPack(user, packId)) {
            sessionRowDTO.setAnswered(new Date());
            sessionService.saveSessionRow(sessionRowDTO, user);
        }
    }

}