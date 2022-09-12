package com.example.demo0.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameplanet;


    @OneToMany(mappedBy = "planet", fetch = FetchType.EAGER)
    private Collection<Human> tenants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameplanet() {
        return nameplanet;
    }

    public void setNameplanet(String nameplanet) {
        this.nameplanet = nameplanet;
    }

    public Collection<Human> getTenants() {
        return tenants;
    }

    public void setLast(Collection<Human> tenants) {
        this.tenants = tenants;
    }
}
