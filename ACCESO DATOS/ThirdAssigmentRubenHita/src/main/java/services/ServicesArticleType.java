package services;

import io.vavr.control.Either;
import model.ArticleTypeHib;

import java.util.List;

public interface ServicesArticleType {
    Either<String, List<ArticleTypeHib>> getAll();
}
