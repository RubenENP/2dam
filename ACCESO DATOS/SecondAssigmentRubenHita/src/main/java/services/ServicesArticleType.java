package services;

import io.vavr.control.Either;
import model.ArticleType;

import java.util.List;

public interface ServicesArticleType {
    Either<List<ArticleType>, String> getAllTypes();
    Either<List<String>, String> getAllNameTypes();
}
