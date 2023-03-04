package com.example.graphqlrubenhita.spring.rest;

import com.example.graphqlrubenhita.domain.models.Article;
import com.example.graphqlrubenhita.domain.usecases.ArticleService;
import org.springframework.data.relational.core.sql.In;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }
    @QueryMapping
    public List<Article> getAllArticles(){return articleService.getAllArticles();}

    @QueryMapping
    public Article getArticleById(Integer id) {
        return articleService.getArticleById(id);
    }

    @MutationMapping
    public Integer updateArticle(@Argument int id, @Argument String nombre, @Argument String descripcion){
        return articleService.updateArticle(id, nombre, descripcion);
    }

    @MutationMapping
    public void deleteArticle(@PathVariable int id) {
        articleService.deleteArticle(id);
    }
}
