package dao;

import io.vavr.control.Either;
import model.ArticleType;

import java.util.List;

public interface DaoArticlesType {
    Either<List<ArticleType>, String> getAll();
}
