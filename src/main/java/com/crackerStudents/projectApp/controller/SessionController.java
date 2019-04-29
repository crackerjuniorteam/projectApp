package com.crackerStudents.projectApp.controller;


import com.crackerStudents.projectApp.DTO.CardDTO;
import com.crackerStudents.projectApp.convert.JSONview;
import com.crackerStudents.projectApp.domain.Card;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.service.SessionService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @GetMapping("session/{name}")
    @JsonView(JSONview.QuestionAndAnswer.class)
    public List<CardDTO> allCards(@PathVariable String name, Model model, @AuthenticationPrincipal User user) {
        return sessionService.getDTOCardsFromPack(name);
    }


}