package services;

import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import modelo.User;
import retrofit2.Response;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface ServicesUser {
    Single<Either<String, User>> postUser(User user);
    Single<Either<String, String>> logout(String logout);
    Single<Either<String, User>> login(String user, String pass) throws NoSuchAlgorithmException;
    Single<Either<String, List<User>>> getAll();
}
