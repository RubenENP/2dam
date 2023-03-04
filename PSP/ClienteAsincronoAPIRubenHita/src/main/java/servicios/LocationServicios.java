package servicios;

import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import modelo.locations.Results;

import java.util.List;

public interface LocationServicios {
    Single<Either<String, List<Results>>> getAllLocations();
}
