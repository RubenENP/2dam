package dao;

import model.Article;

import java.util.List;

public interface DaoArticles {
    List<Article> getAll();
    List<Article> getAll(int type);
    List<Article> getAllWithRating();
    Article save(Article article);
    Article delete(Article article);
}
