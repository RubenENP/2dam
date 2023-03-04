package dao;

import io.vavr.control.Either;
import model.Query;

import java.util.List;

public interface DaoQueries {
    Either<List<Query>, String> query1();
}
