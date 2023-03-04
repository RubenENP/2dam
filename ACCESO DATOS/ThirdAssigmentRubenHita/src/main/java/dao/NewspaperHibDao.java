package dao;

import io.vavr.control.Either;
import model.NewspaperHib;

import java.util.List;

public interface NewspaperHibDao {
    Either<String, List<NewspaperHib>> getAll();
    Either<String, NewspaperHib> get(NewspaperHib newspaperHib);
    Either<String, NewspaperHib> add(NewspaperHib newspaper);
    Either<String, NewspaperHib> delete(NewspaperHib newspaper);
}
