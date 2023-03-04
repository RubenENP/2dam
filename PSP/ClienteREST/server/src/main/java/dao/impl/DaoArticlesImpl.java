package dao.impl;

import dao.DBConnectionPool;
import dao.DaoArticles;

import domain.model.Article;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import domain.model.mapper.ArticleMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.SQLQueries;

import java.sql.*;
import java.util.List;

@Log4j2
public class DaoArticlesImpl implements DaoArticles {
    private ResultSet rs;
    private DBConnectionPool pool;

    @Inject
    DaoArticlesImpl(DBConnectionPool pool) {
        this.pool = pool;
    }

    public List<Article> getAll() {
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());

        return jtm.query(SQLQueries.SELECT_ARTICLES, new ArticleMapper());
    }

    public List<Article> getAll(int type) {
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());

        String query = SQLQueries.SELECT_ARTICLES_BY_TYPE;

        return jtm.query(query, new ArticleMapper(), type);
    }

    public List<Article> getAllWithRating(){
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());

        String query = SQLQueries.SELECT_ARTICLES_RATING_LOWER_5;

        return jtm.query(query, new ArticleMapper());
    }

    public Article save(Article article) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(pool.getDataSource());
        jdbcTemplate.update(SQLQueries.INSERT_ARTICLE, article.getName_article(), article.getDescription(),
                article.getId_type(), article.getId_newspaper());

        return article;
    }

    public Article delete(Article article) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(pool.getDataSource());
        jdbcTemplate.update(SQLQueries.DELETE_ARTICLE, article.getId());

        return article;
    }
}
