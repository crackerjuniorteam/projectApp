package com.crackerStudents.projectApp.controller;

import com.crackerStudents.projectApp.domain.Card;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.repos.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private CardRepo cardRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Card> cards = cardRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            cards = cardRepo.findByAnswer(filter);
        } else {
            cards = cardRepo.findAll();
        }

        model.addAttribute("cards", cards);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String question,
            @RequestParam String answer, Map<String, Object> model
    ) {
        Card card = new Card(question, answer, user);

        cardRepo.save(card);

        Iterable<Card> cards = cardRepo.findAll();

        model.put("cards", cards);
        model.put("filter", "");

        return "main";
    }
}
