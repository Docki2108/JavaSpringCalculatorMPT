package com.example.demo0.controllers;

import com.example.demo0.models.HuggyWuggy;
import com.example.demo0.reposytories.HuggyRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

    @Controller
    @RequestMapping("/huggywuggy")
    public class HuggyController {

        @Autowired
        private HuggyRep huggyRep;

        @GetMapping("/")
        public String huggyIndex(Model model){
            Iterable<HuggyWuggy> huggy = huggyRep.findAll();
            model.addAttribute("huggy", huggy);
            return "huggywuggy/HuggyIndex";
        }

        @GetMapping("/addHuggy")
        public String addViewHuggy(Model model){
            return "/huggywuggy/HuggyAdd"; // обращение к файлу внутри темплате
        }

        @PostMapping("/addHuggy")
        public String addHuggy(@RequestParam("name") String name,
                          @RequestParam("primary_color") String primary_color,
                          @RequestParam("gender") String gender,
                          @RequestParam("height") Integer height,
                          @RequestParam("creation_date") Integer creation_date,
                          Model model){
            HuggyWuggy newsOne = new HuggyWuggy(name, primary_color, gender, height, creation_date);
            huggyRep.save(newsOne);
            return "redirect:/huggywuggy/"; //прописывание url
        }

        @GetMapping("/searchHuggy")
        public String search1(@RequestParam("name") String name,
                             Model model){
            List<HuggyWuggy> huggyList = huggyRep.findByName(name);
            model.addAttribute("huggy", huggyList);
            return "redirect:/huggywuggy/";
        }

        @PostMapping("/searchHuggy")
        public String search2(@RequestParam("name") String name,
                             Model model){
            List<HuggyWuggy> huggyList = huggyRep.findByNameContains(name);
            model.addAttribute("huggy", huggyList);
            return "/huggywuggy/HuggyIndex";
        }
    }
