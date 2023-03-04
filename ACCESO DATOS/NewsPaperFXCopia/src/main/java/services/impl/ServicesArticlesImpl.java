package services.impl;

import dao.DaoArticles;
import jakarta.inject.Inject;
import model.Article;
import services.ServicesArticles;

import java.util.List;

public class ServicesArticlesImpl implements ServicesArticles {
    DaoArticles daoArticles;

    @Inject
    ServicesArticlesImpl(DaoArticles daoArticles) {
        this.daoArticles = daoArticles;
    }

    public List<Article> getAllArticles() {
        return daoArticles.getAll();
    }

    public List<Article> getArticlesByType(int type) {
        List<Article> articleList = daoArticles.getAll();
        return articleList.stream().filter(article -> article.getIdType() == type).toList();
    }

    public boolean addArticle(String line) {
        return daoArticles.save(line);
    }
}
