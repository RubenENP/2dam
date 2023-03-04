package servicios;

import domain.model.Article;
import io.vavr.control.Either;

import java.util.List;

public interface ServicesArticles {
    List<Article> getAllArticles();

    Either<List<Article>, String> getArticlesByType(String descriptionType);

    Either<List<Article>, String> getArticlesByRating();

    Either<Article, String> addArticle(String name, String description, String newspaperName, String articletypeName);

    Either<Article, String> addArticle(String name, String description, int newspaperId, int articletypeId);

    Either<List<String>, String> getAllArticlesName();
}
