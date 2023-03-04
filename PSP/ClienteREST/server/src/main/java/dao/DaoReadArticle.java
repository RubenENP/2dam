package dao;

import domain.model.readArticle.ReadArticle;
import io.vavr.control.Either;

import java.util.List;

public interface DaoReadArticle {
    Either<List<ReadArticle>, String> getAll();
    int save(ReadArticle readArticle);
}
