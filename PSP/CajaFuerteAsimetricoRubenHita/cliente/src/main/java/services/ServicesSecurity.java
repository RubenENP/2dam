package services;

import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

public interface ServicesSecurity {
    Single<Either<String, String>> getServerPublicKey();
}
