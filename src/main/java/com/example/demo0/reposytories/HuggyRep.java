package com.example.demo0.reposytories;

import com.example.demo0.models.HuggyWuggy;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface HuggyRep extends CrudRepository<HuggyWuggy, Long>{
    public List<HuggyWuggy> findByName(String name);
    public List<HuggyWuggy> findByNameContains(String name);
}
