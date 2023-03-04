package services;

import io.vavr.control.Either;
import jakarta.xml.bind.JAXBException;
import model.reader.Reader;

import java.io.IOException;
import java.util.List;

public interface ServicesReaders {
    Either<List<Reader>, String> getAll();

    Either<List<Reader>, String> getReadersByArticleType(String name);

    Either<List<Reader>, String> getReadersByNewspaper(String newspaperName);
    Either<List<String>, String> getReadersName();

    boolean delete(int id);
}
