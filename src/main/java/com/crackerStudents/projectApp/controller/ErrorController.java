package com.crackerStudents.projectApp.controller;

import com.crackerStudents.projectApp.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    @GetMapping("/error")
    public String getErrorPage(Model model, @AuthenticationPrincipal User user) {
        return "error";
    }
}