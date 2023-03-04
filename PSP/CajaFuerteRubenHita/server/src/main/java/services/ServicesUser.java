package services;

import io.vavr.control.Either;
import modelo.User;

import java.util.List;

public interface ServicesUser {
    List<User> getAll();
    Either<String, User> register(User u);

    Either<String, User> login(User u);

    Either<String, User> comprobarUsuario(String caller);
}
