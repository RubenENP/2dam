package dao;

import domain.model.Subscription;
import io.vavr.control.Either;

import java.util.List;

public interface DaoSubscriptions {
    Either<List<Subscription>, String> getAll(int idReader);

    Subscription save(Subscription s);

    Subscription delete(Subscription s);
}
