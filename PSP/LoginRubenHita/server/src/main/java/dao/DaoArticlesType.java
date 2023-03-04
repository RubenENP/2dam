package dao;

import domain.model.ArticleType;
import io.vavr.control.Either;

import java.util.List;

public interface DaoArticlesType {
    Either<List<ArticleType>, String> getAll();
}
