package dao;

import io.vavr.control.Either;
import model.Article;
import model.Newspaper;

import java.util.List;

public interface ArticleHibDao{
    int delete(Newspaper newspaper);

    Either<String, List<Article>> getAll(Newspaper newspaper);
}
