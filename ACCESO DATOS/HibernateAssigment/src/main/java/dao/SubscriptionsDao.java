package dao;

import io.vavr.control.Either;
import model.Subscription;

import java.util.List;

public interface SubscriptionsDao {
    Either<String, List<Subscription>> get(int idReader);
}
