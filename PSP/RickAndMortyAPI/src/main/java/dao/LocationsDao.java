package dao;

import io.vavr.control.Either;
import modelo.locations.Results;

import java.io.IOException;
import java.util.List;

public interface LocationsDao {
    Either<String, List<Results>> getAllLocations() throws IOException;
}
