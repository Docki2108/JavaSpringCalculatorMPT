package com.example.demo0.controllers;

import com.example.demo0.models.News;
import com.example.demo0.reposytories.NewsReposytories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/news")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class NewsController {

    @Autowired
    private NewsReposytories newsReposytories;

    @GetMapping("/")
    public String index(Model model){
        Iterable<News> news = newsReposytories.findAll();
        model.addAttribute("news", news);
        return "news/index";
    }

    @GetMapping("/add")
    public String addView(Model model){
        model.addAttribute("news", new News());
        return "news/add-news"; // обращение к файлу внутри темплате
    }

//    @PostMapping("/add")
//    public String add(@RequestParam("title") String title,
//                      @RequestParam("author") String author,
//                      @RequestParam("body_text") String bodyText,
//                      @RequestParam("views") Integer views,
//                      @RequestParam("likes") Integer likes,
//                      Model model){
//        News newsOne = new News(title, bodyText, author, views, likes);
//        newsReposytories.save(newsOne);
//        return "redirect:/news/"; //прописывание url
//    }

    @PostMapping("/add")
    public String add(@ModelAttribute("news") @Valid News newNews,
                      BindingResult bindingResult,
                      Model model){
        if (bindingResult.hasErrors())
            return "news/add-news";
        newsReposytories.save(newNews);
        return "redirect:/news/"; //прописывание url
    }

    @GetMapping("/search")
    public String search(@RequestParam("title") String title,
                      Model model){
        List<News> newsList = newsReposytories.findByTitle(title);
        model.addAttribute("news", newsList);
        return "/news/index";
    }

    @GetMapping("/{id}")
    public String read(@PathVariable("id") Long id,
                         Model model)
    {
        Optional<News> news = newsReposytories.findById(id);
        ArrayList<News> newsArrayList = new ArrayList<>();
        news.ifPresent(newsArrayList::add);

        model.addAttribute("news", newsArrayList);
        return "news/info-news";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,
                       Model model)
    {
        News news = newsReposytories.findById(id).orElseThrow();
        newsReposytories.delete(news);

        //newsReposytories.deleteById(id);
        return "redirect:/news/";
    }

    @PostMapping("/delete/{id}")
    public String deleteNews(@PathVariable("id") Long id,
                         Model model)
    {
        News news = newsReposytories.findById(id).orElseThrow();
        newsReposytories.delete(news);
        return "redirect:/news/";
    }

    @PostMapping("/edit/{id}")
    public String editNews(@PathVariable("id") Long id, Model model,
                           @ModelAttribute("news") @Valid News news,
                           BindingResult bindingResult
                           )
    {
        if(!newsReposytories.existsById(id)){
            return "redirect:/news/";
        }
        if(bindingResult.hasErrors()){
            return "news/edit-news";
        }
        news.setId(id);
        newsReposytories.save(news);
        return "redirect:/news/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                       Model model)
    {
        if (!newsReposytories.existsById(id)) {
            return "redirect:/news/";
        }
        Optional<News> news = newsReposytories.findById(id);
        ArrayList<News> newsArrayList = new ArrayList<>();
        news.ifPresent(newsArrayList::add);
        model.addAttribute("news", newsArrayList.get(0));
        return "news/edit-news";
    }
}