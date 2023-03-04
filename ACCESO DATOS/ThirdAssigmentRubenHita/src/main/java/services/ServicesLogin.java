package services;

import io.vavr.control.Either;
import model.Login;

public interface ServicesLogin {
    Either<String, Login> get(String userName);
}
