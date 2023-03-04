package services;

import io.vavr.control.Either;
import model.Newspaper;
import model.Subscription;

import java.sql.Date;
import java.util.List;

public interface ServicesSubscriptions {
    Either<List<Subscription>, String> getAll(int idReader);

    Either<Subscription, String> addSubscription(int idNewspaper, int idReader, Date startDate, Date cancellationDate);

    Either<Subscription, String> delete(Subscription s);
}
