package dao;

import com.google.gson.Gson;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import modelo.ApiError;
import okhttp3.MediaType;
import retrofit2.HttpException;
import retrofit2.Response;

import java.io.EOFException;
import java.util.Objects;

@Log4j2
public abstract class DaoGenerics {
    public static final String APPLICATION_JSON = "application/json";
    private final Gson gson;

    @Inject
    public DaoGenerics(Gson gson) {
        this.gson = gson;
    }

    public <T> Single<Either<String, T>> safeAPICall(Single<T> apiCall) {
        return apiCall.map(t -> Either.right(t).mapLeft(Object::toString))
                .subscribeOn(Schedulers.io())
                .onErrorReturn(this::getError);
    }

    public Single<Either<String, Boolean>> safeAPICallToDelete(Single<Response<Void>> apiCall) {
        return apiCall.map(objectResponse -> {
            Either<String, Boolean> error = Either.left("Error de comunicacion");
            if (objectResponse.isSuccessful()){
                error = Either.right(true).mapLeft(Object::toString);
            }
            assert objectResponse.errorBody() != null;
            if (Objects.equals(objectResponse.errorBody().contentType(), MediaType.get(APPLICATION_JSON))){
                ApiError apiError = gson.fromJson(objectResponse.errorBody().charStream(), ApiError.class);
                error = Either.left(apiError.getMessage());
            }

            return error;
        });
    }

    private <T> Either<String, T> getError(Throwable throwable) {
        Either<String, T> error = Either.left("Error de comunicacion");

        if (throwable instanceof HttpException) {
            int code = ((HttpException) throwable).code();
            if (Objects.equals(((HttpException) throwable).response().errorBody().contentType(), MediaType.get("application/json"))) {
                ApiError api = gson.fromJson(((HttpException) throwable).response().errorBody().charStream(), ApiError.class);
                error = Either.left(code + " " + api.getMessage());
            }else {
                error = Either.left(((HttpException) throwable).response().message());
            }
        }
        return error;
    }
}
