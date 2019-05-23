package com.crackerStudents.projectApp.controller.global;

import com.crackerStudents.projectApp.DTO.PackDTO;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.service.PackService;
import com.crackerStudents.projectApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@PreAuthorize("hasAuthority('USER')")

public class GlobalMainController {
    private PackService packService;
    private UserService userService;

    @Autowired
    public GlobalMainController(PackService packService, UserService userService) {
        this.packService = packService;
        this.userService = userService;
    }

    @GetMapping("/global")
    public String mainPage(Model model, @PageableDefault Pageable pageable) {
        Page<PackDTO> page = packService.getAllPackDTOByPublic(pageable);
        model.addAttribute("page", page);
        model.addAttribute("url", "/global");
        return "global";
    }

    @PostMapping("/global")
    public String addPackByUser(@AuthenticationPrincipal User user, @RequestParam UUID id) {
        user.addPack(packService.getPackById(id));
        userService.saveUser(user);
        return "redirect:/main";
    }
}
