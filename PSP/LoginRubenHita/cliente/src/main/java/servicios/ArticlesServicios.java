package servicios;

import domain.model.Article;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

import java.util.List;

public interface ArticlesServicios {
    Single<Either<String, List<Article>>> getAllArticles();

    Single<Either<String, List<Article>>> getArticleByName(String name);

    Single<Either<String, Article>> addArticle(String name, String description, int typeId, int newspaperId);
}
