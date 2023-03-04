package services;

import io.vavr.control.Either;
import model.Article;

import java.util.List;

public interface ServicesArticles {
    Either<List<Article>, String> getAllArticles();

    Either<List<Article>, String> getArticlesByType(int type);

    boolean addArticle(String line);

    Either<List<String>, String> getAllArticlesName();
}
