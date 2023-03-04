package com.example.springrubenhita.data.spring.rest;

import com.example.springrubenhita.domain.models.Article;
import com.example.springrubenhita.domain.models.Newspaper;
import com.example.springrubenhita.domain.usecases.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }
    @GetMapping
    public List<Article> getAll(){return articleService.getAllArticles();}

    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable int id) {
        return articleService.getArticleById(id);
    }

    @PutMapping("/{id}")
    public Article updateArticle(@PathVariable int id, @RequestBody Article article) {
        return articleService.updateArticle(id, article);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable int id) {
        articleService.deleteArticle(id);
    }
}
