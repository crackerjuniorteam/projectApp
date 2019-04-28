package com.crackerStudents.projectApp.controller;


import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.service.SessionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {


    private SessionService sessionService;
/*
    @GetMapping("/packs")
    public String view(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("packs", user.getPacks());
        return "packs";
    }*/

    @GetMapping("/session/{name}")
    public String allCards(@PathVariable String name, Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("cards", sessionService.getDTOCardsFromPack(name));
        return "jsonTemplate";
    }


}