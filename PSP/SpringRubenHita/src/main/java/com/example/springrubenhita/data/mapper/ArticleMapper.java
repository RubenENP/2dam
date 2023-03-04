package com.example.springrubenhita.data.mapper;

import com.example.springrubenhita.data.modelo.ArticleEntity;
import com.example.springrubenhita.data.modelo.NewspaperEntity;
import com.example.springrubenhita.domain.models.Article;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {
    public ArticleEntity toArticleEntity(Article a, NewspaperEntity n){
        return new ArticleEntity(a.getId(), a.getNombre(), a.getDescripcion(), n);
    }

    public Article toArticle(ArticleEntity a){
        return new Article(a.getId(), a.getNombre(), a.getDescripcion());
    }
}
