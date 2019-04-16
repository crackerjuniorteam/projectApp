package com.crackerStudents.projectApp.controller;

import com.crackerStudents.projectApp.domain.Role;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.repos.UserRepo;
import com.crackerStudents.projectApp.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserSevice userSevice;

    @GetMapping("/reg")
    public String registration() {
        return "reg";
    }

    @PostMapping("/reg")
    public String addUser(User user, Map<String, Object> model) {
        if (!userSevice.addUser(user)) {
            model.put("message", "User exists!");
            return "reg";
        }
        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userSevice.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("message", "Activation code is not found!");
        }

        return "login";
    }
}