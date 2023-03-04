package services;

import io.vavr.control.Either;
import model.Newspaper;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface ServicesNewspaper {
    Either<String, List<Newspaper>> getAll();

    Either<String, Newspaper> delete(Newspaper newspaper);
}
