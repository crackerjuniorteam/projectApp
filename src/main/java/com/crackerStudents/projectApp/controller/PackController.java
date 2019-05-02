package com.crackerStudents.projectApp.controller;


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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;
import java.util.Set;

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

    @GetMapping("/packs/{name}")
    public String main(@PathVariable String name, Model model, @AuthenticationPrincipal User user) {
        Set<Pack> packs = user.getPacks();
        Pack ob;
        for (Pack el : packs) {
            if (el.getName().equals(name)) {
                ob = el;
                model.addAttribute("pack", ob);
                model.addAttribute("cards", ob.getCards());
                model.addAttribute("time", ob.getCreated().toString());
            }
        }
        return "pack";
    }

    @PostMapping("/packs/{namePack}")
    public String addCard(@Valid Card card, BindingResult bindingResult, Model model, @PathVariable String namePack, @AuthenticationPrincipal User user) {
        Object[] elem = user.getPacks().stream().filter(el -> el.getName().equals(namePack)).toArray();
        Pack pack = (Pack) elem[0];
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.addAttribute("card", card);
            model.addAttribute("pack", pack);
            model.addAttribute("cards", pack.getCards());
            model.addAttribute("time", pack.getCreated().toString());
            model.mergeAttributes(errors);
            return "pack";
        } else {
            card.setAuthor(user);
            //cardRepo.save(card);
            pack.addCard(card);
            packRepo.save(pack);
            model.addAttribute("card", null);
            model.addAttribute("pack", pack);
            model.addAttribute("cards", pack.getCards());
            model.addAttribute("time", pack.getCreated().toString());
            return "pack";
        }
    }
}