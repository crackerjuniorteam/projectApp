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

import java.util.List;

@Controller
public class PackController {
    @Autowired
    PackRepo packRepo;

    @GetMapping("/createpack")
    public String view(Model model) {
        return "createpack";
    }

    @PostMapping("/createpack")
    public String createPack(@RequestParam String elem, @AuthenticationPrincipal User user, Model model) {
        Pack pack = new Pack(elem, user.getId(), true);
        packRepo.save(pack);
        pack.addUser(user);
        user.addPack(pack);
        return "createpack";
    }
}
