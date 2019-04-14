package com.crackerStudents.projectApp.controller;


import com.crackerStudents.projectApp.domain.Card;
import com.crackerStudents.projectApp.domain.Pack;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.repos.CardRepo;
import com.crackerStudents.projectApp.repos.PackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@Controller
public class PackController {
    @Autowired
    CardRepo cardRepo;

    @Autowired
    PackRepo packRepo;

    @GetMapping("/packs")
    public String view(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("packs", user.getPacks());
        return "packs";
    }

    @GetMapping("/packs/{name}")
    public String main(@PathVariable String name, Model model, @AuthenticationPrincipal User user) {
        List<Pack> packs = user.getPacks();
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
    public String addCard(@RequestParam String question, @RequestParam String answer, @PathVariable String namePack, @AuthenticationPrincipal User user, Model model) {
        Object[] elem = user.getPacks().stream().filter(el -> el.getName().equals(namePack)).toArray();
        Pack ob = (Pack) elem[0];
        Card card = new Card(question, answer, user);
        cardRepo.save(card);
        ob.addCard(card);
        packRepo.save(ob);
        model.addAttribute("pack", ob);
        model.addAttribute("cards", ob.getCards());
        model.addAttribute("time", ob.getCreated().toString());
        return "pack";
    }

}