package services;

import io.vavr.control.Either;
import model.Newspaper;
import model.Reader;

import java.util.List;

public interface ServicesReader {
    Either<String, List<Reader>> getAll();
    Either<String, List<Reader>> getAll(Newspaper newspaper);
    Either<Integer, Reader> save(String nameReader, String cancellationDate, Newspaper newspaper);
    Either<Integer, Reader> update(Reader reader, Newspaper newspaper ,Integer id);
    Either<String, Reader> delete(Reader reader);
}
