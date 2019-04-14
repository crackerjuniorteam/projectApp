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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class CreatePackController {
    @Autowired
    PackRepo packRepo;

    @GetMapping("/createpack")
    public String view(Model model) {

        return "createpack";
    }

    @PostMapping("/createpack")
    public String createPack(@RequestParam String name, @AuthenticationPrincipal User user, Model model) {
        Pack pack = new Pack(name, user.getId(), true, 0, new Date());
        Object[] elem = user.getPacks().stream().filter(el -> el.getName().equals(name)).toArray();
        if (elem.length != 0) {
            model.addAttribute("message", "Такой пак уже существует");
        } else {
            pack.addUser(user);
            user.addPack(pack);
            packRepo.save(pack);
        }
        return "createpack";
    }
}