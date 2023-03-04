package services;

import io.vavr.control.Either;

public interface ServicesLogin {
    boolean loginAdmin(String password);
    Either<Integer, String> loginUser(String user, String password);
}
