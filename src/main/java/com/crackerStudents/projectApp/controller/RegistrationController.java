package com.crackerStudents.projectApp.controller;

import com.crackerStudents.projectApp.dao.UserRepository;
import com.crackerStudents.projectApp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(" ")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userRepository.findByLogin(user.getLogin());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }

       // user.setActive(true);
       // user.setRoles(Collections.singleton("ROLE_USER"));
        userRepository.save(user);

        return "redirect:/login";
    }
}
