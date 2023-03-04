package dao;

import io.vavr.control.Either;
import model.Login;

import java.util.List;

public interface DaoLogin {
    Either<List<Login>, String> getAll();
}
