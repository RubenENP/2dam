package services;

import io.vavr.control.Either;
import model.Article;
import model.Newspaper;

import java.util.List;

public interface ServicesArticle {
    //    Either<String, List<Article>> getAll();
    Either<String, String> delete(Newspaper newspaper);

    Either<String, List<Article>> getAll(Newspaper newspaper);
}
