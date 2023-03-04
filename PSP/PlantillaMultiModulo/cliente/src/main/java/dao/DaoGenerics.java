package dao;

import com.google.gson.Gson;
import common.Constantes;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

import java.io.IOException;
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

    public Single<Either<String, Boolean>> safeAPICallToDelete(Single<Response<Object>> apiCall) {
        return apiCall.map(objectResponse -> objectResponse.isSuccessful() ?
                        Either.right(true).mapLeft(Object::toString) :
                        Either.right(false).mapLeft(Object::toString))
                .subscribeOn(Schedulers.io())
                .onErrorReturn(this::getError);
    }

    private <T> Either<String, T> getError(Throwable throwable) {
        Either<String, T> error = Either.left(Constantes.COMMUNICATION_ERROR);
        if (throwable instanceof HttpException httpException) {
            try (ResponseBody responseBody = Objects.requireNonNull(httpException.response()).errorBody()) {
                if (Objects.equals(Objects.requireNonNull(responseBody).contentType(),
                        MediaType.get(APPLICATION_JSON))) {
                    Error apierror = gson.fromJson(responseBody.string(), Error.class);
                    error = Either.left(apierror.getMessage());
                } else {
                    error = Either.left(Objects.requireNonNull(httpException.response()).message());
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        return error;
    }


}
