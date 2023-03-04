package com.example.springrubenhita.domain.usecases;

import com.example.springrubenhita.data.mapper.ArticleMapper;
import com.example.springrubenhita.data.modelo.ArticleEntity;
import com.example.springrubenhita.data.spring.repository.ArticleEntityRepository;
import com.example.springrubenhita.domain.exceptions.NotFoundException;
import com.example.springrubenhita.domain.models.Article;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
public class ArticleService {
    private final ArticleEntityRepository articleEntityRepository;

    private final ArticleMapper articleMapper;

    public ArticleService(ArticleEntityRepository articleEntityRepository, ArticleMapper articleMapper) {
        this.articleEntityRepository = articleEntityRepository;
        this.articleMapper = articleMapper;
    }

    @Transactional
    public List<Article> getAllArticles(){
        return articleEntityRepository.findAll()
                .stream()
                .map(articleMapper::toArticle)
                .toList();
    }

    @Transactional
    public Article getArticleById(int id) {
        return articleEntityRepository.findById(id)
                .map(articleMapper::toArticle)
                .orElseThrow(() -> new NotFoundException("Article not found"));

    }

    public Article updateArticle(int id, Article article) {
        int numeroFilasUpdate = articleEntityRepository.updateArticleValues(article.getNombre()
                , article.getDescripcion(), id);
        if (numeroFilasUpdate == 0) {
            throw new NotFoundException("Usuario no encontrado");
        }
        return article;
    }

    public void deleteArticle(int id) {
        try {
            articleEntityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Article not found");
        }
    }
}
