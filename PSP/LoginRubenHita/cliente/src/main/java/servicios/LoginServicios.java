package servicios;

import domain.model.Usuario;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

public interface LoginServicios {
    Single<Either<String, Usuario>> login(String user, String pass);
    Single<Either<String, Usuario>> register(String user, String pass, String email);
    Single<Either<String, String>> cambiarPass(String user);
    Single<Either<String, String>> logout();
}
