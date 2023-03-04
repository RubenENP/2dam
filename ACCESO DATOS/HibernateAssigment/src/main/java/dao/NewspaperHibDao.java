package dao;

import io.vavr.control.Either;
import model.NewspaperHib;

import java.util.List;
import java.util.Map;

public interface NewspaperHibDao {
    Either<String, List<NewspaperHib>> getAll();
    Either<String, NewspaperHib> get(NewspaperHib newspaperHib);
    Either<String, NewspaperHib> add(NewspaperHib newspaper);
    int delete(NewspaperHib newspaper);
    Map<String, Integer> getNbrArticles(int newspaperId);
}
