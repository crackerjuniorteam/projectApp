package com.crackerStudents.projectApp.controller;


import com.crackerStudents.projectApp.domain.Pack;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.repos.PackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/packs")
public class PackController {

    @Autowired
    PackRepo packRepo;

    @GetMapping
    public String view(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("packs", user.getPacks());
        return "packs";
    }

    @GetMapping("{name}")
    public String main(@PathVariable String name, Model model, @AuthenticationPrincipal User user) {
        List<Pack> packs = user.getPacks();
        Pack ob;
        for(Pack el: packs) {
            if (el.getName().equals(name)) {
                ob = el;
                model.addAttribute("pack", ob);
                model.addAttribute("time", ob.getCreated().toString());
            }
        }
        return "pack";
    }

}
