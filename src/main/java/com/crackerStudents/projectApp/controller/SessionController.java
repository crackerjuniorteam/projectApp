package com.crackerStudents.projectApp.controller;


import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/session/{packId}")
    public String sessionStart(@PathVariable UUID packId, Model model, @AuthenticationPrincipal User user) {
        if (sessionService.userHasAccessToPack(user, packId)){
            model.addAttribute("packId", packId);
            return "session";
        }
        else {

            return "error";
        }
    }
}