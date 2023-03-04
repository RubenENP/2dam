package dao;

import domain.model.ArticleType;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

import java.util.List;

public interface ArticleTypeDao {
    Single<Either<String, List<ArticleType>>> getAllArticleType();
}
