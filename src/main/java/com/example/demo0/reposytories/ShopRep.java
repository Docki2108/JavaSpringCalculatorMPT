package com.example.demo0.reposytories;

import com.example.demo0.models.Shop;
import org.springframework.data.repository.CrudRepository;

public interface ShopRep extends CrudRepository<Shop, Long> {
    Shop findByName(String name);
}
