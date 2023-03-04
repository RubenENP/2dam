package servicios;

import domain.model.reader.Reader;
import io.vavr.control.Either;

import java.time.LocalDate;
import java.util.List;

public interface ServicesReaders {
    List<Reader> getAll();

    int delete(int id);

    int addReader(int parseInt, String name, LocalDate birthDate);

    int update(String newName, int id, LocalDate birthDate);

    Either<List<Reader>, String> getAllByNewspaperId(int newspaperId);

    Either<List<Reader>, String> getAllByArticleTypeId(int articleTypeId);
}
