package com.example.demo0.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String name;
    @ManyToMany
    @JoinTable(name="coca_shop",
            joinColumns=@JoinColumn(name="shop_id"),
            inverseJoinColumns=@JoinColumn(name="coca_id"))
    private List<Coca> cocas;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Coca> getCocas() {
        return cocas;
    }

    public void setStudents(List<Coca> cocas) {
        this.cocas = cocas;
    }
}
