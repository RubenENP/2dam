package services;

import model.Article;

import java.util.List;

public interface ServicesArticles {
    List<Article> getAllArticles();

    List<Article> getArticlesByType(int type);

    boolean addArticle(String line);
}
