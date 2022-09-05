package com.example.demo0.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HuggyWuggy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name, primary_color, gender;
    Integer height, creation_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimary_color() {
        return primary_color;
    }

    public void setPrimary_color(String primary_color) {
        this.primary_color = primary_color;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Integer creation_date) {
        this.creation_date = creation_date;
    }

    public HuggyWuggy(String name, String primary_color, String gender, Integer height, Integer creation_date) {
        this.name = name;
        this.primary_color = primary_color;
        this.gender = gender;
        this.height = height;
        this.creation_date = creation_date;
    }

    public HuggyWuggy() {
    }
}
