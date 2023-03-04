package services;

import io.vavr.control.Either;
import model.Query;

import java.util.List;

public interface ServicesQuery {
    Either<List<Query>, String> getQuery();
}
