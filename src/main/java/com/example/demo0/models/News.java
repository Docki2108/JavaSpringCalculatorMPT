package com.example.demo0.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Строка не может быть меньше 3", min = 3, max = 100)
    String title;

    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Строка не может быть меньше 1", min = 1, max = 100)
    String body_text;

    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Строка не может быть меньше 1", min = 1, max = 100)
    String author;

    @Min(message = "Впишите число не меньше 1", value = 1)
    @Max(message = "Число не может быть больше 10000", value = 10000)
    @NotNull(message = "Укажите количество просмотров")
    Integer views;

    @Min(message = "Впишите число не меньше 0", value = 0)
    @Max(message = "Число не может быть больше 10000", value = 10000)
    @NotNull(message = "Укажите количество лайков")
    Integer likes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody_text() {
        return body_text;
    }

    public void setBody_text(String body_text) {
        this.body_text = body_text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public News(String title, String body_text, String author, Integer views, Integer likes) {
        this.title = title;
        this.body_text = body_text;
        this.author = author;
        this.views = views;
        this.likes = likes;
    }

    public News() {
    }
}
