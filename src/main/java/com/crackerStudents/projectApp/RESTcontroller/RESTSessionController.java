package com.crackerStudents.projectApp.RESTcontroller;


import com.crackerStudents.projectApp.DTO.CardDTO;
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
            
            UUID session_uuid = sessionService.getActiveSessionForUser(user);
            CardDTO cardDTO = sessionService.getNextCard(packId, session_uuid);
            SessionGETdto sessionGETdto = new SessionGETdto(cardDTO.getQuestion(), cardDTO.getAnswer(), session_uuid,
                    packId, cardDTO.getId(), true);
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
                                 @RequestBody SessionGETdto sessionGETdto,
                                 @AuthenticationPrincipal User user)
    {
        if (sessionService.userHasAccessToPack(user, packId)) {
            System.out.println("----------------------------------");
            System.out.println(sessionGETdto.getCard_id());
            System.out.println(sessionGETdto.getReply());
            sessionService.saveSessionRow(sessionGETdto, user);
            sessionService.updateCard(sessionGETdto);
        }
    }

    @PutMapping("rest/session/{packId}")
    public void endSession(@PathVariable UUID packId,
                                 @RequestBody SessionGETdto sessionGETdto,
                                 @AuthenticationPrincipal User user)
    {
        if (sessionService.userHasAccessToPack(user, packId)) {
            sessionService.endSession(sessionGETdto);
        }
    }

}