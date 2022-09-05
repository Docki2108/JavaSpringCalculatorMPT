package com.example.demo0.reposytories;

import com.example.demo0.models.Abebe;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface AbebeRep extends CrudRepository<Abebe, Long> {
    public List<Abebe> findByName(String name);
    public List<Abebe> findByNameContains(String name);
}
