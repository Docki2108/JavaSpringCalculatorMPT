package com.example.demo0.reposytories;

import com.example.demo0.models.Coca;
import org.springframework.data.repository.CrudRepository;

public interface CocaRep extends CrudRepository<Coca, Long> {
    Coca findByName(String name);
}
