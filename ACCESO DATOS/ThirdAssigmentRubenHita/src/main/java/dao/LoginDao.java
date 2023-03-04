package dao;

import io.vavr.control.Either;
import model.Login;

public interface LoginDao {
    Either<String, Login> get(String userName);
    Either<String, Login> add(Login login);
    Either<String, Login> delete(Login login);
}
