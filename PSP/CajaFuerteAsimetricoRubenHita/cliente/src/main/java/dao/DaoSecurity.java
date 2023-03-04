package dao;

import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

import java.security.NoSuchAlgorithmException;

public interface DaoSecurity {
    Single<Either<String, String>> getServerPublicKey();
    Single<Either<String, String>> crearClaves() throws NoSuchAlgorithmException;
}
