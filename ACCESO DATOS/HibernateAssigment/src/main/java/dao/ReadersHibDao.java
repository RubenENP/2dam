package dao;

import io.vavr.control.Either;
import model.ReaderHib;

import java.util.List;

public interface ReadersHibDao {
    Either<String, List<ReaderHib>> getAll();
    Either<String, ReaderHib> get(int id);
    Either<String, List<ReaderHib>> getAll(String description);
    Either<String, List<ReaderHib>> getAllByNewspaper(String newspaperName);
    Either<String, ReaderHib> add(ReaderHib reader);
    Either<String, ReaderHib> delete(ReaderHib reader);
    Either<String, ReaderHib> update(ReaderHib reader);
}
