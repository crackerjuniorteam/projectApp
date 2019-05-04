package com.crackerStudents.projectApp.controller;


import com.crackerStudents.projectApp.DTO.PackDTO;
import com.crackerStudents.projectApp.domain.Card;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.service.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PackController {

    private final PackService packService;

    @Autowired
    public PackController(PackService packService) {
        this.packService = packService;
    }

    @GetMapping("/packs")
    public String view(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("packs", packService.getUserPacks(user));
        return "packs";
    }

    @GetMapping("/packs/{packName}")
    public String main(@PathVariable String packName, Model model, @AuthenticationPrincipal User user) {
        PackDTO pack = packService.getPackByUserAndName(packName, user);
        model.addAttribute("pack", pack);
        model.addAttribute("cards", pack.getCards());
        model.addAttribute("time", pack.getCreated().toString());
        return "pack";
    }

    //to do: Binding result сделать красиво
    @PostMapping("/packs/{namePack}")
    public String addCard(Card card, BindingResult bindingResult, Model model,
                          @PathVariable String namePack, @AuthenticationPrincipal User user) {

        card.setAuthor(user);
        packService.AddCardAndSave(card, namePack, user);
        return "redirect:/packs/" + namePack;
    }
}