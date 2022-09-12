package com.example.demo0.controllers;

import com.example.demo0.models.HuggyWuggy;
import com.example.demo0.models.News;
import com.example.demo0.reposytories.HuggyRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            model.addAttribute("huggywuggy", new HuggyWuggy());
            return "huggywuggy/HuggyAdd"; // обращение к файлу внутри темплате
        }

        @PostMapping("/addHuggy")
        public String addHuggy(@ModelAttribute("huggywuggy") @Valid HuggyWuggy newHuggy,
                          BindingResult bindingResult,
                          Model model){
            if(bindingResult.hasErrors())
                return "huggy/HuggyAdd";
            huggyRep.save(newHuggy);
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

    @GetMapping("/{id}")
    public String read(@PathVariable("id") Long id,
                       Model model)
    {
        Optional<HuggyWuggy> huggy = huggyRep.findById(id);
        ArrayList<HuggyWuggy> huggyArrayList = new ArrayList<>();
        huggy.ifPresent(huggyArrayList::add);

        model.addAttribute("huggywuggy", huggyArrayList);
        return "huggywuggy/HuggyInfo";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,
                         Model model)
    {
        HuggyWuggy huggy = huggyRep.findById(id).orElseThrow();
        huggyRep.delete(huggy);
        return "redirect:/huggywuggy/";
    }

    @PostMapping("/delete/{id}")
    public String deleteHuggy(@PathVariable("id") Long id,
                             Model model)
    {
        HuggyWuggy huggy = huggyRep.findById(id).orElseThrow();
        huggyRep.delete(huggy);
        return "redirect:/huggywuggy/";
    }



    @PostMapping("/edit/{id}")
    public String editHuggy(@PathVariable("id") Long id, Model model,
                           @ModelAttribute("huggywuggy") @Valid HuggyWuggy huggyWuggy,
                           BindingResult bindingResult
    )
    {
        if(!huggyRep.existsById(id)){
            return "redirect:/huggywuggy/";
        }
        if(bindingResult.hasErrors()){
            return "huggywuggy/HuggyEdit";
        }
        huggyWuggy.setId(id);
        huggyRep.save(huggyWuggy);
        return "redirect:/huggywuggy/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                       Model model)
    {
        if (!huggyRep.existsById(id)) {
            return "redirect:/huggywuggy/";
        }
        Optional<HuggyWuggy> huggywuggy = huggyRep.findById(id);
        ArrayList<HuggyWuggy> huggyWuggyArrayList= new ArrayList<>();
        huggywuggy.ifPresent(huggyWuggyArrayList::add);
        model.addAttribute("huggywuggy", huggyWuggyArrayList.get(0));
        return "huggywuggy/HuggyEdit";
    }
    }
