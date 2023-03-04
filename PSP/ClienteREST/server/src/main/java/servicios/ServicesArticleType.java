package servicios;

import domain.model.ArticleType;
import io.vavr.control.Either;

import java.util.List;

public interface ServicesArticleType {
    Either<List<ArticleType>, String> getAllTypes();
    Either<List<String>, String> getAllNameTypes();
}
