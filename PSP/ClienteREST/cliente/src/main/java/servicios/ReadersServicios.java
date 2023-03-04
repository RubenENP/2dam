package servicios;

import domain.model.reader.Reader;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

import java.time.LocalDate;
import java.util.List;

public interface ReadersServicios {
    Single<Either<String, List<Reader>>> getAllReaders();
    Single<Either<String, Reader>> getReaderById(int id);
    Single<Either<String, Reader>> addReader(String name, LocalDate birthDate);
    Single<Either<String, Reader>> updateReader(int id, String name, LocalDate birthDate);
}
