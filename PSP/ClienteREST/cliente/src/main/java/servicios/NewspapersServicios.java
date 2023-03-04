package servicios;

import domain.model.Newspaper;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

import java.time.LocalDate;
import java.util.List;

public interface NewspapersServicios {
    Single<Either<String, List<Newspaper>>> getAllNewspapers();
    Single<Either<String, Newspaper>> getUnNewspaper(int id);
    Single<Either<String, Newspaper>> postNewspaper(int id, String name, LocalDate date);
    Single<Either<String, Boolean>> deleteNewspaper(int id);
}
