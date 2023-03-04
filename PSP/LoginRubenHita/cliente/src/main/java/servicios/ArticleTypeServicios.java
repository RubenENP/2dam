package servicios;

import domain.model.ArticleType;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

import java.util.List;

public interface ArticleTypeServicios {
    Single<Either<String, List<ArticleType>>> getAllArticleType();
}
