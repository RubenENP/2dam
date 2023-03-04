package dao;

import io.vavr.control.Either;
import model.Newspaper;
import model.Reader;

import java.util.List;

public interface ReaderDao {
    Either<String, List<Reader>> getAll();
    Either<String, List<Reader>> getAll(Newspaper newspaper);
    int save(Reader reader, Newspaper newspaper);
    int update(Reader reader, Newspaper newspaper ,Integer id);
}
