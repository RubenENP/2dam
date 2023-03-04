package services.impl;

import dao.ReadArticleDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Article;
import model.Newspaper;
import model.ReadArticle;
import services.ServicesReadArticle;

public class ServicesReadArticleImpl implements ServicesReadArticle {
    ReadArticleDao readArticleDao;

    @Inject
    public ServicesReadArticleImpl(ReadArticleDao readArticleDao) {
        this.readArticleDao = readArticleDao;
    }

    @Override
    public Either<String, ReadArticle> add(ReadArticle readArticles, Newspaper newspaper, Article article) {
        return readArticleDao.add(readArticles, newspaper, article);
    }
}
