package com.crackerStudents.projectApp.controller;

import com.crackerStudents.projectApp.DTO.PackDTO;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.service.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('USER')")
public class NewPackController {
    private final PackService packService;
    private int checkCreate = 0; // 1 - успешно создан, 0 - только пришли на страницу, -1 - такой пак уже существует

    @Autowired
    public NewPackController(PackService packService) {
        this.packService = packService;
    }

    @GetMapping("/createPack")
    public String view(Model model) {
        model.addAttribute("checkCreate", checkCreate);
        checkCreate = 0;
        return "createPack";
    }

    @PostMapping("/createPack")
    public String createPack(@RequestParam String packName, @AuthenticationPrincipal User user, Model model, @RequestParam Map<String, String> radios) {
        boolean packPublic = true;
        if (!radios.keySet().contains("public")){
            packPublic = false;
        }
        if (packService.packExists(packName, user)) {
            checkCreate = -1;
        } else {
            packService.createPack(packName, user, packPublic);
            checkCreate = 1;
        }
        return "redirect:/createPack";
    }
}