package com.example.demo0.controllers;

import com.example.demo0.models.Role;
import com.example.demo0.models.User;
import com.example.demo0.reposytories.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
//@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {

    @Autowired
    private UserRep userRep;

    @GetMapping("/")
    public String all_users(Model model) {
        model.addAttribute("users",
                userRep.findAll());
        return "InfoUsers";
    }

    @GetMapping("/EditUsers/{id}")
    public String user_role(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<User> user_raw = userRep.findById(id);
        ArrayList<User> userArrayList = new ArrayList<>();
        user_raw.ifPresent(userArrayList::add);
        model.addAttribute("one_user", userArrayList);
        model.addAttribute("roles", Role.values());
        return "EditUsers";
    }

    @PostMapping
    public String EditRole(
            @RequestParam("userId") User user,
            @RequestParam("login") String login,
            @RequestParam(name = "roles[]", required = false)
            String[] roles)
    {
        user.setLogin(login);
        user.getRoles().clear();
        if (roles != null) {
            for (String role_name : roles
            ) {
                user.getRoles().add(Role.valueOf(role_name));
            }
        }
        userRep.save(user);
        return "redirect:/admin/";
    }
}