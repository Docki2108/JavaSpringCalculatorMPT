package com.example.demo0.reposytories;

import com.example.demo0.models.News;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

    public interface NewsReposytories extends CrudRepository<News, Long> {
    public List<News> findByTitle(String title);
    public List<News> findByTitleContains(String title);
}
