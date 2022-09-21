package com.example.demo0.controllers;

import com.example.demo0.models.Abebe;
import com.example.demo0.models.News;
import com.example.demo0.reposytories.AbebeRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@PreAuthorize("hasAnyAuthority('ADMIN, USER')")
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


    @GetMapping("/{id}")
    public String readAbebe(@PathVariable("id") Long id,
                       Model model)
    {
        Optional<Abebe> abebe = abebeRep.findById(id);
        ArrayList<Abebe> abebeArrayList = new ArrayList<>();
        abebe.ifPresent(abebeArrayList::add);

        model.addAttribute("abebe", abebeArrayList);
        return "abebe/AbebeInfo";
    }

    @GetMapping("/Abebedelete/{id}")
    public String deleteabebe(@PathVariable("id") Long id,
                         Model model)
    {
        Abebe abebe = abebeRep.findById(id).orElseThrow();
        abebeRep.delete(abebe);
        return "redirect:/abebe/";
    }

    @PostMapping("/Abebedelete/{id}")
    public String deleteabebe1(@PathVariable("id") Long id,
                             Model model)
    {
        Abebe abebe = abebeRep.findById(id).orElseThrow();
        abebeRep.delete(abebe);
        return "redirect:/abebe/";
    }

//    @PostMapping("/edit/{id}")
//    public String editNews(@PathVariable("id") Long id,
//                         @RequestParam("title") String title,
//                         @RequestParam("author") String author,
//                           @RequestParam("body_text") String body_text,
//                           @RequestParam("views") Integer views,
//                           @RequestParam("likes") Integer likes,
//                         Model model)
//    {
//        News news = newsReposytories.findById(id).orElseThrow();
//        news.setTitle(title);
//        news.setAuthor(author);
//        news.setBody_text(body_text);
//        news.setViews(views);
//        news.setLikes(likes);
//
//        newsReposytories.save(news);
//        return "redirect:/news/";
//    }

    @PostMapping("/editAbebe/{id}")
    public String editAbebe(@PathVariable("id") Long id, Model model,
                           @ModelAttribute("abebe") @Valid Abebe abebe,
                           BindingResult bindingResult
    )
    {
        if(!abebeRep.existsById(id)){
            return "redirect:/abebe/";
        }
        if(bindingResult.hasErrors()){
            return "abebe/AbebeEdit";
        }
        abebe.setId(id);
        abebeRep.save(abebe);
        return "redirect:/abebe/";
    }

    @GetMapping("/abebe/{id}")
    public String editAbebe(@PathVariable("id") Long id,
                       Model model)
    {
        if (!abebeRep.existsById(id)) {
            return "redirect:/abebe/";
        }
        Optional<Abebe> abebe = abebeRep.findById(id);
        ArrayList<Abebe> abebeArrayList = new ArrayList<>();
        abebe.ifPresent(abebeArrayList::add);
        model.addAttribute("abebe", abebeArrayList.get(0));
        return "abebe/AbebeEdit";
    }
}
