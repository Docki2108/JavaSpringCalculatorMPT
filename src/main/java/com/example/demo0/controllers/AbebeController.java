package com.example.demo0.controllers;

import com.example.demo0.models.Abebe;
import com.example.demo0.reposytories.AbebeRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/abebe")
public class AbebeController {

    @Autowired
    private AbebeRep abebeRep;

    @GetMapping("/")
    public String abebeIndex(Model model){
        Iterable<Abebe> abebe = abebeRep.findAll();
        model.addAttribute("abebe", abebe);
        return "abebe/AbebeIndex";
    }

    @GetMapping("/addAbebe")
    public String addViewAbebe(Model model){
        return "/abebe/AbebeAdd"; // обращение к файлу внутри темплате
    }

    @PostMapping("/addAbebe")
    public String addAbebe(@RequestParam("name") String name,
                           @RequestParam("info") String info,
                           @RequestParam("city") String city,
                           @RequestParam("attack") Integer attack,
                           @RequestParam("armor") Integer armor,
                           Model model){
        Abebe abebeOne = new Abebe(name, info, city, attack, armor);
        abebeRep.save(abebeOne);
        return "redirect:/abebe/"; //прописывание url
    }

    @GetMapping("/searchAbebe")
    public String search3(@RequestParam("name") String name,
                          Model model){
        List<Abebe> abebeList = abebeRep.findByName(name);
        model.addAttribute("abebe", abebeList);
        return "redirect:/abebe/";
    }

    @PostMapping("/searchAbebe")
    public String search4(@RequestParam("name") String name,
                          Model model){
        List<Abebe> abebeList = abebeRep.findByNameContains(name);
        model.addAttribute("abebe", abebeList);
        return "/abebe/AbebeIndex";
    }
}
