package com.crackerStudents.projectApp.RESTcontroller;


import com.crackerStudents.projectApp.DTO.CardDTO;
import com.crackerStudents.projectApp.DTO.SessionDTO;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.repos.SessionRepo;
import com.crackerStudents.projectApp.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> allCards(@PathVariable UUID packId, @AuthenticationPrincipal User user) {
        if (sessionService.userHasAccessToPack(user, packId) && sessionService.packHasCards(packId)){
            System.out.println("Session created!");
            UUID session_uuid = sessionService.getActiveSessionForUser(user, packId);
            CardDTO cardDTO = sessionService.getNextCard(packId, session_uuid);
            System.out.println(sessionService.cardsToRepeatToday(packId));
            SessionDTO sessionDTO = new SessionDTO(cardDTO.getQuestion(), cardDTO.getAnswer(), session_uuid,
                    packId, cardDTO.getId(), true, sessionService.cardsToRepeatToday(packId));
            return new ResponseEntity<>(sessionDTO, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(
                    "Access denied message here", new HttpHeaders(), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("rest/session/{packId}")
    public void saveSessionStats(@PathVariable UUID packId,
                                 @RequestBody SessionDTO sessionDTO,
                                 @AuthenticationPrincipal User user)
    {
        if (sessionService.userHasAccessToPack(user, packId)) {
            sessionService.saveSessionRow(sessionDTO, user);
            sessionService.updateCard(sessionDTO);
        }
    }

    @PutMapping("rest/session/{packId}")
    public void endSession(@PathVariable UUID packId,
                                 @RequestBody SessionDTO sessionDTO,
                                 @AuthenticationPrincipal User user)
    {
        if (sessionService.userHasAccessToPack(user, packId)) {
            sessionService.endSession(sessionDTO);
        }
    }

}