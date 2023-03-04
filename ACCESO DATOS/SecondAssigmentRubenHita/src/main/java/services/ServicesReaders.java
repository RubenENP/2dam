package services;

import io.vavr.control.Either;
import jakarta.xml.bind.JAXBException;
import model.reader.Reader;

import java.io.IOException;
import java.util.List;

public interface ServicesReaders {
    Either<List<Reader>, String> getAll();

    int delete(int id);

    int addReader(int parseInt, String name, String birthDate);

    int update(String newName, int id, String birthDate);

    Either<List<Reader>, String> getAllByNewspaperId(int newspaperId);

    Either<List<Reader>, String> getAllByArticleTypeId(int articleTypeId);
}
