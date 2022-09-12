package com.example.demo0.reposytories;

import com.example.demo0.models.Planet;
import org.springframework.data.repository.CrudRepository;

public interface PlanetRep extends CrudRepository<Planet, Long> {
    Planet findByNameplanet(String nameplanet);
}
