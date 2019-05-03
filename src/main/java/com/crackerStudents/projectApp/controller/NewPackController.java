package com.crackerStudents.projectApp.controller;

import com.crackerStudents.projectApp.DTO.PackDTO;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.service.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewPackController {

    private final PackService packService;

    @Autowired
    public NewPackController(PackService packService){
        this.packService = packService;
    }

    @GetMapping("/createPack")
    public String view(Model model) {

        return "createPack";
    }

    @PostMapping("/createPack")
    public String createPack(@RequestParam String packName, @AuthenticationPrincipal User user, Model model) {
        PackDTO packDTO = packService.createPackDTO(packName, user.getId());
        if (packService.packExists(packDTO)) {
            model.addAttribute("message", "Такой пак уже существует");
        } else {
            packService.createPack(packDTO, user);
        }
        return "createPack";
    }
}