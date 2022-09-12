package com.example.demo0.controllers;

import com.example.demo0.models.Role;
import com.example.demo0.models.User;
import com.example.demo0.reposytories.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.Collections;

@Controller
public class RegistrationController  {

    @Autowired
    private UserRep userRep;

    @GetMapping("/registration")
    public String regView(Model model){
        return "Registration";
    }

    @PostMapping("/registration")
    public String regAction(User user, Model model){
        User userFromDB = userRep.findByLogin(user.getLogin());
        if(userFromDB != null){
            model.addAttribute("error", "Пользователь уже существует");
            return "Registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRep.save(user);
        return "redirect:/login";
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
}
