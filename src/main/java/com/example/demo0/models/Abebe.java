package com.example.demo0.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Abebe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Строка не может быть меньше 1", min = 1, max = 100)
    String name;

    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Строка не может быть меньше 2", min = 2, max = 100)
    String info;

    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Строка не может быть меньше 3", min = 3, max = 100)
    String city;

    @Min(message = "Впишите число не меньше 1", value = 1)
    @Max(message = "Число не может быть больше 10000", value = 10000)
    @NotNull(message = "Укажите количество просмотров")
    Integer attack;

    @Min(message = "Впишите число не меньше 1", value = 1)
    @Max(message = "Число не может быть больше 10000", value = 10000)
    @NotNull(message = "Укажите количество просмотров")
    Integer armor;

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
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public Integer getAttack() {
        return attack;
    }
    public void setAttack(Integer attack) {
        this.attack = attack;
    }
    public Integer getArmor() {
        return armor;
    }
    public void setArmor(Integer armor) {
        this.armor = armor;
    }
    public Abebe(String name, String info, String city, Integer attack, Integer armor) {
        this.name = name;
        this.info = info;
        this.city = city;
        this.attack = attack;
        this.armor = armor;
    }
    public Abebe() {
    }
}
