package dao;

import io.vavr.control.Either;
import model.readArticle.ReadArticle;

import java.util.List;

public interface DaoReadArticle {
    Either<List<ReadArticle>, String> getAll();

    boolean addReadArticle(int id, int idReader, int idArticle, int rating);
}
