package dao;

import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import modelo.User;

import java.util.List;

public interface DaoUser {
    Single<Either<String, User>> postUser(User user);
    Single<Either<String, String>> logout(String logout);
    Single<Either<String, User>> login(String user, String pass);
    Single<Either<String, List<User>>> getAll();
}
