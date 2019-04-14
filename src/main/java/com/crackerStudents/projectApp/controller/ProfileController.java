package com.crackerStudents.projectApp.controller;

import com.crackerStudents.projectApp.domain.Role;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ProfileController {
    @Autowired
    UserRepo userRepo;

    @GetMapping("/profile")
    public String view(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/profileEdit")
    public String viewEdit(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "profileEdit";
    }

    @PostMapping("/profileEdit")
    public String userSave(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @AuthenticationPrincipal User user
    ) {
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        userRepo.save(user);

        return "redirect:/profile";
    }
}