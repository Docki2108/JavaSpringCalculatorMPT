package com.example.demo0.controllers;

import com.example.demo0.models.Abebe;
import com.example.demo0.models.News;
import com.example.demo0.reposytories.AbebeRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String addView(Model model){
        model.addAttribute("abebe", new Abebe());
        return "/abebe/AbebeAdd"; // обращение к файлу внутри темплате
    }

    @PostMapping("/addAbebe")
    public String addAbebe(@ModelAttribute("abebe") @Valid Abebe newAbebe,
                           BindingResult bindingResult,
                           Model model){
        if (bindingResult.hasErrors())
            return "abebe/AbebeAdd";
        abebeRep.save(newAbebe);
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
