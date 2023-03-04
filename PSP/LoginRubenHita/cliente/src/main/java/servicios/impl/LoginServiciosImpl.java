package servicios.impl;

import dao.LoginDao;
import domain.model.Usuario;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import servicios.LoginServicios;

public class LoginServiciosImpl implements LoginServicios {
    private LoginDao loginDao;

    @Inject
    public LoginServiciosImpl(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    public Single<Either<String, Usuario>> login(String user, String pass) {
        return loginDao.login(user, pass);
    }

    public Single<Either<String, Usuario>> register(String user, String pass, String email) {
        return loginDao.register(user, pass, email);
    }

    public Single<Either<String, String>> cambiarPass(String user) {
        return loginDao.cambiarPass(user);
    }

    public Single<Either<String, String>> logout() {
        return loginDao.logout();
    }
}
