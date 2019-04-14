package com.crackerStudents.projectApp.controller;

import com.crackerStudents.projectApp.domain.Role;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class ProfileController {
    @Autowired
    UserRepo userRepo;

    @Value("${pathNameUpload}")
    private String uploadPath;

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
            @RequestParam("file") MultipartFile file,
            @AuthenticationPrincipal User user
    ) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            user.setAvatar(resultFilename);
        }
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        userRepo.save(user);

        return "redirect:/profile";
    }
}