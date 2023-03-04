package com.example.graphqlrubenhita.domain.usecases;

import com.example.graphqlrubenhita.data.mapper.ArticleMapper;
import com.example.graphqlrubenhita.domain.exceptions.NotFoundException;
import com.example.graphqlrubenhita.domain.models.Article;
import com.example.graphqlrubenhita.spring.repository.ArticleEntityRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.graphql.data.method.annotation.Argument;
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

    public List<Article> getAllArticles(){
        return articleEntityRepository.findAll()
                .stream()
                .map(articleMapper::toArticle)
                .toList();
    }

    public Article getArticleById(int id) {
        return articleEntityRepository.findById(id)
                .map(articleMapper::toArticle)
                .orElseThrow(() -> new NotFoundException("Article not found"));

    }

    public int updateArticle(int id, String nombre, String descripcion) {
        int numeroFilasUpdate = articleEntityRepository.updateArticleValues(nombre
                , descripcion, id);
        if (numeroFilasUpdate == 0) {
            throw new NotFoundException("Usuario no encontrado");
        }
        return numeroFilasUpdate;
    }

    public void deleteArticle(int id) {
        try {
            articleEntityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Article not found");
        }
    }
}
