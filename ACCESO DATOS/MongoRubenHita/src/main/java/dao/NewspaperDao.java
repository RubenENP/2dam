package dao;

import io.vavr.control.Either;
import model.Newspaper;

import java.util.List;

public interface NewspaperDao {
    Either<String, List<Newspaper>> getAll();
    int delete(Newspaper newspaper);
}
