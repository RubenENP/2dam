package services.impl;

import dao.DaoArticles;
import io.vavr.control.Either;
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

    public Either<List<Article>, String> getAllArticles() {
        Either<List<Article>, String> response;

        if (daoArticles.getAll().isLeft()){
            response = Either.left(daoArticles.getAll().getLeft());
        } else {
            response = Either.right(daoArticles.getAll().get());
        }

        return response;
    }

    public Either<List<Article>, String> getArticlesByType(int type) {
        Either<List<Article>, String> response;

        if (daoArticles.getAll().isLeft()) {
            List<Article> articleList = daoArticles.getAll().getLeft();
            response = Either.left(articleList.stream().filter(article -> article.getIdType() == type).toList());
        } else {
            response = Either.right(daoArticles.getAll().get());
        }

        return response;
    }

    public boolean addArticle(String line) {
        return daoArticles.save(line);
    }

    public Either<List<String>, String> getAllArticlesName() {
        Either<List<String>, String> response;

        if (daoArticles.getAll().isLeft()){
            response = Either.left(daoArticles.getAll().getLeft().stream().map(Article::getTitle).toList());
        } else {
            response = Either.right(daoArticles.getAll().get());
        }

        return response;
    }
}
