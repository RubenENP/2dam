package servicios.impl;

import dao.DaoSubscriptions;
import domain.model.Subscription;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import servicios.ServicesSubscriptions;

import java.sql.Date;
import java.util.List;

public class ServicesSubscriptionsImpl implements ServicesSubscriptions {
    DaoSubscriptions daoSubscriptions;

    @Inject
    ServicesSubscriptionsImpl(DaoSubscriptions daoSubscriptions){
        this.daoSubscriptions = daoSubscriptions;
    }

    public Either<List<Subscription>, String> getAll(int idReader){
        return daoSubscriptions.getAll(idReader);
    }

    public Either<Subscription, String> addSubscription(int idNewspaper, int idReader, Date startDate, Date cancellationDate) {
        Either<Subscription, String> response;

        Subscription s = new Subscription(idNewspaper, idReader, startDate, cancellationDate);
        Subscription subscription = daoSubscriptions.save(s);

        if (subscription == s){
            response = Either.left(subscription);
        } else {
            response = Either.right("ERROR");
        }

        return response;
    }

    public Either<Subscription, String> delete(Subscription s){
        Either<Subscription, String> result;
        Subscription subscription = daoSubscriptions.delete(s);

        if (subscription != null){
            result = Either.left(subscription);
        } else {
            result = Either.right("ERROR");
        }

        return result;
    }
}
