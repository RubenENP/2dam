package services.impl;

import dao.DaoQueries;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Query;
import services.ServicesQuery;

import java.util.List;

public class ServicesQueryImpl implements ServicesQuery {
    private final DaoQueries daoQueries;

    @Inject
    public ServicesQueryImpl(DaoQueries daoQueries) {
        this.daoQueries = daoQueries;
    }

    public Either<List<Query>, String> getQuery(){
        return daoQueries.query1();
    }
}
