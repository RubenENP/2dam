package services.impl;

import dao.ArticleHibDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Article;
import model.NewspaperHib;
import services.ServicesArticle;

import java.util.List;

public class ServicesArticleImpl implements ServicesArticle {
    private final ArticleHibDao articleHibDao;

    @Inject
    public ServicesArticleImpl(ArticleHibDao articleHibDao) {
        this.articleHibDao = articleHibDao;
    }

    public Either<String, List<Article>> getAll() {
        return articleHibDao.getAll();
    }

    public Either<String, List<Article>> delete(int newspaperId) {
        return articleHibDao.delete(newspaperId);
    }
}
