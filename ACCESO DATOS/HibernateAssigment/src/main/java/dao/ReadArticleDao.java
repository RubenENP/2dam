package dao;

import io.vavr.control.Either;
import model.ReadArticle;

import java.util.Map;

public interface ReadArticleDao {
    Either<String, ReadArticle> add(ReadArticle readArticle);
    Map<Double, String> getAvgRating(int reader);
}
