package services;

import io.vavr.control.Either;
import model.ReadArticle;

import java.util.Map;

public interface ServicesReadArticle {
    Either<String, ReadArticle> add(ReadArticle readArticle);
    Map<Double, String> getAvgRating(int reader);
}
