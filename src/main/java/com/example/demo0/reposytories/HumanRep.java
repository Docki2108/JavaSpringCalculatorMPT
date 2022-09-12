package com.example.demo0.reposytories;

import com.example.demo0.models.Human;
import org.springframework.data.repository.CrudRepository;

public interface HumanRep extends CrudRepository<Human, Long> {
}
