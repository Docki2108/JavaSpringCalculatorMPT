package com.example.demo0.reposytories;

import com.example.demo0.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRep extends CrudRepository<User, Long> {
    User findByLogin(String login);
}
