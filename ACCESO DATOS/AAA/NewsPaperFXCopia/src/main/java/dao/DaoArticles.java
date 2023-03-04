package dao;

import io.vavr.control.Either;
import model.Article;

import java.util.List;

public interface DaoArticles {
    Either<List<Article>, String> getAll();

    boolean save(String line);

    boolean delete(int id);
}
