package services;

import io.vavr.control.Either;
import model.Article;

import java.util.List;

public interface ServicesArticles {
    Either<List<Article>, String> getAllArticles();

    Either<List<Article>, String> getArticlesByType(String descriptionType);

    Either<List<Article>, String> getArticlesByRating();

    Either<Article, String> addArticle(String name, String description, String newspaperName, String articletypeName);

    Either<List<String>, String> getAllArticlesName();
}
