package services;

import io.vavr.control.Either;
import model.NewspaperHib;

import java.sql.Date;
import java.util.List;

public interface ServicesNewspaper {
    Either<String, List<NewspaperHib>> getAll();
    Either<String, NewspaperHib> get(NewspaperHib newspaperHib);

    Either<String, NewspaperHib> addNewspaper(String name, Date releaseDate);

    Either<String, NewspaperHib> deleteNewspaper(NewspaperHib newspaper);
}
