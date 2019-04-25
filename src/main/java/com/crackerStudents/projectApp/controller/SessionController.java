package com.crackerStudents.projectApp.controller;


import com.crackerStudents.projectApp.domain.Pack;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.repos.CardRepo;
import com.crackerStudents.projectApp.repos.PackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SessionController {
    @Autowired
    CardRepo cardRepo;

    @Autowired
    PackRepo packRepo;

    @GetMapping("/session/packs")
    public String view(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("packs", user.getPacks());
        return "packs";
    }

    @GetMapping("/session/{name}")
    public String main(@PathVariable String name, Model model, @AuthenticationPrincipal User user) {
        List<Pack> packs = user.getPacks();
        Pack ob;
        for (Pack el : packs) {
            if (el.getName().equals(name)) {
                ob = el;
                model.addAttribute("pack", ob);
                model.addAttribute("cards", ob.getCards());
            }
        }
        return "session";
    }


}