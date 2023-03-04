package servicios;

import domain.model.reader.Reader;
import io.vavr.control.Either;

import java.time.LocalDate;
import java.util.List;

public interface ServicesReaders {
    List<Reader> getAll();
    Either<String, Reader> get(int id, String username);

    int delete(int id);

    Either<String, Reader>  addReader(int parseInt, String name, LocalDate birthDate, String username);

    int update(String newName, int id, LocalDate birthDate);

}
