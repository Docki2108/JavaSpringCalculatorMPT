package com.example.demo0.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String greeting( String name, Model model) {
        model.addAttribute("name", " это k4LЬКUЛЯt00RRRRRR");
        return "Home";
    }

    @PostMapping("/res")
    public String PostResult(@RequestParam(value = "variable1", defaultValue = "0") int a,
                             @RequestParam(value = "variable2", defaultValue = "0") int b,
                             @RequestParam(value = "operations", defaultValue = "+")
                                 String operation,  Model model) {
        int c = switch (operation) {
            case ("+") -> a + b;
            case ("-") -> a - b;
            case ("*") -> a * b;
            case ("/") -> a / b;
            default -> 0;
        };
        model.addAttribute("answer", c);
        return "Result";
    }

    @GetMapping("/res")
    public String GetResult(@RequestParam(value = "variable1", defaultValue = "0") int a,
                             @RequestParam(value = "variable2", defaultValue = "0") int b,
                             @RequestParam(value = "operations", defaultValue = "+")
                             String operation,  Model model) {
        int c = switch (operation) {
            case ("+") -> a + b;
            case ("-") -> a - b;
            case ("*") -> a * b;
            case ("/") -> a / b;
            default -> 0;
        };
        model.addAttribute("answer", c);
        return "Result";
    }
}