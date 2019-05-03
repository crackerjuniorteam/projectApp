package com.crackerStudents.projectApp.controller;


import com.crackerStudents.projectApp.DTO.PackDTO;
import com.crackerStudents.projectApp.domain.Card;
import com.crackerStudents.projectApp.domain.Pack;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.repos.CardRepo;
import com.crackerStudents.projectApp.repos.PackRepo;
import com.crackerStudents.projectApp.service.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PackController {
    @Autowired
    CardRepo cardRepo;

    @Autowired
    PackRepo packRepo;

    @Autowired
    PackService packService;

    @GetMapping("/packs")
    public String view(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("packs", user.getPacks());
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

    @PostMapping("/packs/{PackName}")
    public String addCard(@Valid Card card, /*BindingResult bindingResult,*/ Model model,
                          @PathVariable String PackName, @AuthenticationPrincipal User user) {

            card.setAuthor(user);

            packService.AddCardAndSave(card,PackName);
            Pack pack = packService.getPackByName(PackName);
            model.addAttribute("card", null);
            model.addAttribute("pack", pack);
            model.addAttribute("cards", pack.getCards());
            model.addAttribute("time", pack.getCreated().toString());
            return "pack";
    }
}