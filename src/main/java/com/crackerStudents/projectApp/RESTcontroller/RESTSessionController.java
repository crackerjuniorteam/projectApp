package com.crackerStudents.projectApp.RESTcontroller;


import com.crackerStudents.projectApp.DTO.CardDTO;
import com.crackerStudents.projectApp.DTO.SessionDTO;
import com.crackerStudents.projectApp.DTO.SessionGETdto;
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

import java.net.URI;
import java.util.Date;
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
    public ResponseEntity<SessionGETdto> allCards(@PathVariable UUID packId, @AuthenticationPrincipal User user) {
        if (sessionService.userHasAccessToPack(user, packId)){
            UUID uuid = sessionService.getActiveSessionForUser(user);
            CardDTO cardDTO = sessionService.getNextCard(packId);
            SessionGETdto sessionGETdto = new SessionGETdto(cardDTO.getQuestion(), cardDTO.getAnswer(), uuid, packId);
            return new ResponseEntity<>(sessionGETdto, HttpStatus.OK);
        }
        else {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("/error"));
            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
        }
    }

    @PostMapping("rest/session/{packId}")
    public void saveSessionStats(@PathVariable UUID packId,
                                 @RequestBody SessionDTO sessionDTO,
                                 @AuthenticationPrincipal User user)
    {
        if (sessionService.userHasAccessToPack(user, packId)) {
            System.out.println(sessionDTO.getAnswer());
            System.out.println(sessionDTO.getId());
            System.out.println(sessionDTO.getIsActive());
            sessionDTO.setAnswered(new Date());
            sessionService.saveSessionRow(sessionDTO, user);
        }
    }

    @PostMapping("rest/session/end/{packId}")
    public void endSession(@PathVariable UUID packId,
                                 @RequestBody SessionDTO sessionDTO,
                                 @AuthenticationPrincipal User user)
    {
        if (sessionService.userHasAccessToPack(user, packId)) {
            sessionDTO.setAnswered(new Date());
            sessionService.saveSessionRow(sessionDTO, user);
        }
    }

}