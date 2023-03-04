package dao;

import domain.model.reader.Reader;
import io.vavr.control.Either;

import java.util.List;

public interface DaoReaders {
    Either<List<Reader>, String> getAll();
    Either<String, Reader> get(int id, String username);
    int delete(Reader reader);

    Either<String, Reader> save(Reader reader, String username);

    int update(Reader reader);
}
