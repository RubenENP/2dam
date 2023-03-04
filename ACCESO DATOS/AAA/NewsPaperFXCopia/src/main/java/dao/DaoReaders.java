package dao;

import io.vavr.control.Either;
import model.reader.Reader;

import java.util.List;

public interface DaoReaders {
    Either<List<Reader>, String> getAll();

    boolean delete(int id);
}
