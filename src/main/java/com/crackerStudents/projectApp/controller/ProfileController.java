package com.crackerStudents.projectApp.controller;

import com.crackerStudents.projectApp.DTO.UserDTO;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@PreAuthorize("hasAuthority('USER')")
public class ProfileController {

    private UserService userService;


    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @Value("${pathNameUpload}")
    private String uploadPath;

    @GetMapping("/profile/{username}")
    public String view(Model model, @PathVariable String username, @AuthenticationPrincipal User user) {
        UserDTO userByProfileDTO = userService.getUserDTOByName(username);
        model.addAttribute("subscriptionsCount", userByProfileDTO.getSubscriptions().size());
        model.addAttribute("subscribersCount", userByProfileDTO.getSubscribers().size());
        model.addAttribute("isSubscriber", userByProfileDTO.getSubscribers().contains(user));
        model.addAttribute("userByProfile", userByProfileDTO);
        return "profile";
    }

    @GetMapping("/profileEdit")
    public String viewEdit(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "profileEdit";
    }

    @PostMapping("/profileEdit")
    public String updateProfile(@AuthenticationPrincipal User user,
                                @RequestParam String username,
                                @RequestParam String password,
                                @RequestParam String email,
                                @RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam("file") MultipartFile file) throws IOException {
        userService.updateProfile(username, password, email, firstName, lastName, file, user);
        return "redirect:/profile/" + user.getUsername();
    }
}