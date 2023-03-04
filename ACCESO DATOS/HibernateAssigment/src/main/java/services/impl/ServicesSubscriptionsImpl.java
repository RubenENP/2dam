package services.impl;

import dao.SubscriptionsDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Subscription;
import services.ServicesReaders;
import services.ServicesSubscriptions;

import java.util.List;

public class ServicesSubscriptionsImpl implements ServicesSubscriptions {
    private final SubscriptionsDao subscriptionsDao;

    @Inject
    public ServicesSubscriptionsImpl(SubscriptionsDao subscriptionsDao) {
        this.subscriptionsDao = subscriptionsDao;
    }

    public Either<String, List<Subscription>> get(int idReader){
        return subscriptionsDao.get(idReader);
    }
}
