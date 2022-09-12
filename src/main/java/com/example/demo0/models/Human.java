package com.example.demo0.models;

import javax.persistence.*;

@Entity
@Table(name = "humans")
public class Human {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Planet planet;

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

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public Human(String name, Planet planet) {
        this.name = name;
        this.planet = planet;
    }

    public Human() {
    }
}
