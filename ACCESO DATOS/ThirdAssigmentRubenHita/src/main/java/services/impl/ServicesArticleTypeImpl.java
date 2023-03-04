package services.impl;

import dao.ArticleTypeHibDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.ArticleTypeHib;
import services.ServicesArticleType;

import java.util.List;

public class ServicesArticleTypeImpl implements ServicesArticleType {
    private final ArticleTypeHibDao articleTypeHibDao;

    @Inject
    public ServicesArticleTypeImpl(ArticleTypeHibDao articleTypeHibDao) {
        this.articleTypeHibDao = articleTypeHibDao;
    }

    public Either<String, List<ArticleTypeHib>> getAll() {
        return articleTypeHibDao.getAll();
    }
}
