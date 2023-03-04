package servicios;

import io.vavr.control.Either;
import modelo.locations.Results;

import java.util.List;

public interface LocationServicios {
    Either<String, List<Results>> getAllLocations();
}
