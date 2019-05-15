package com.crackerStudents.projectApp.controller;


import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/session/{packName}")
    public String sessionStart(@PathVariable String packName, Model model, @AuthenticationPrincipal User user) {
        if (sessionService.userHasAccessToPack(user, packName)){
            model.addAttribute("packName", packName);
            return "session";
        }
        else {

            return "error";
        }
    }
}