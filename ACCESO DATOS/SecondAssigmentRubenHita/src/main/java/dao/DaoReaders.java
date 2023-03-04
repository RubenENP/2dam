package dao;

import io.vavr.control.Either;
import model.reader.Reader;

import java.util.List;

public interface DaoReaders {
    Either<List<Reader>, String> getAll();

    Either<List<Reader>, String> getAllByNewspaperId(int id);

    Either<List<Reader>, String> getAllByArticleTypeId(int id);

    int delete(Reader reader);

    int save(Reader reader);

    int update(Reader reader);
}
