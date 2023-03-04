package dao;

import io.vavr.control.Either;
import model.ArticleTypeHib;

import java.util.List;

public interface ArticleTypeHibDao {
    Either<String, List<ArticleTypeHib>> getAll();
    Either<String, ArticleTypeHib> getReadestArticleType();
}
