package com.example.demo0.controllers;

import com.example.demo0.models.News;
import com.example.demo0.reposytories.NewsReposytories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsReposytories newsReposytories;

    @GetMapping("/a")
    public String index(Model model){
        Iterable<News> news = newsReposytories.findAll();
        model.addAttribute("news", news);
        return "news/index";
    }

    @GetMapping("/add")
    public String addView(Model model){
        return "/news/add-news"; // обращение к файлу внутри темплате
    }

    @PostMapping("/add")
    public String add(@RequestParam("title") String title,
                      @RequestParam("author") String author,
                      @RequestParam("body_text") String bodyText,
                      @RequestParam("views") Integer views,
                      @RequestParam("likes") Integer likes,
                      Model model){
        News newsOne = new News(title, bodyText, author, views, likes);
        newsReposytories.save(newsOne);
        return "redirect:/news/"; //прописывание url
    }

    @GetMapping("/search")
    public String search(@RequestParam("title") String title,
                      Model model){
        List<News> newsList = newsReposytories.findByTitle(title);
        model.addAttribute("news", newsList);
        return "/news/index";
    }
}
