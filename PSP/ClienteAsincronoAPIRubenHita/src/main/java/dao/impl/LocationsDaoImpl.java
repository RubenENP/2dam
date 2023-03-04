package dao.impl;

import dao.LocationsDao;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import modelo.locations.Results;
import dao.retrofit.llamadas.LocationsAPI;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

import java.util.List;
import java.util.Objects;

public class LocationsDaoImpl implements LocationsDao {
    final LocationsAPI locationsAPI;

    @Inject
    LocationsDaoImpl(LocationsAPI locationsAPI){
        this.locationsAPI = locationsAPI;
    }

    public Single<Either<String, List<Results>>> getAllLocations() {
        return locationsAPI.getAllLocations(1)
                .map(rj -> Either.right(rj.results()).mapLeft(Objects::toString))
                .subscribeOn(Schedulers.io())
                .onErrorReturn(throwable -> {
                    Either<String, List<Results>> error = Either.left("Error de comunicacion");
                    if (throwable instanceof HttpException httpException){
                        try (ResponseBody errorBody = Objects.requireNonNull(httpException.response()).errorBody()) {
                            if (!Objects.equals(Objects.requireNonNull(errorBody).contentType(), MediaType.get("application/json"))) {
                                error = Either.left("Error de comunicacion " + Objects.requireNonNull(httpException.response()).message());
                            }
                        }
                    }

                    return error;
                });
    }
}
