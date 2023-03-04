package dao;

import io.vavr.control.Either;
import model.readArticle.ReadArticle;

import java.sql.SQLException;
import java.util.List;

public interface DaoReadArticle {
    Either<List<ReadArticle>, String> getAll();
    int save(ReadArticle readArticle);

    ReadArticle update(ReadArticle readArticle, int rate);
}
