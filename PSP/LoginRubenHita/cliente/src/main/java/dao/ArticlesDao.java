package dao;

import domain.model.Article;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

import java.util.List;

public interface ArticlesDao {
    Single<Either<String, List<Article>>> getAllArticles();

    Single<Either<String, List<Article>>> getArticlesByName(String name);

    Single<Either<String, Article>> addArticle(Article article);
}
