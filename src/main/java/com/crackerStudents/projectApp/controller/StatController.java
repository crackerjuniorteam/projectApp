package com.crackerStudents.projectApp.controller;

import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.print.Pageable;

@Controller
@PreAuthorize("hasAuthority('USER')")
public class StatController {

    private SessionService sessionService;

    @Autowired
    public StatController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/stats")
    public String view(@AuthenticationPrincipal User user, Model model, @PageableDefault Pageable pageable) {

        //model.addAttribute("page", page);
        model.addAttribute("url", "/packs");
        return "stats";
    }
}
