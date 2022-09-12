package com.example.demo0.reposytories;

import com.example.demo0.models.Addres;
import com.example.demo0.models.Person;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.*;

public interface PersonRep extends CrudRepository<Person, Long> {
}

