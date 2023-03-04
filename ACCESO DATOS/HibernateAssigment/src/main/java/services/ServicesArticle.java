package services;

import io.vavr.control.Either;
import model.Article;
import model.NewspaperHib;

import java.util.List;

public interface ServicesArticle {
    Either<String, List<Article>> getAll();
    Either<String, List<Article>>delete(int newspaperId);
}
