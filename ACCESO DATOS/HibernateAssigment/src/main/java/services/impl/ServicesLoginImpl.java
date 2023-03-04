package services.impl;

import dao.LoginDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Login;
import services.ServicesLogin;

public class ServicesLoginImpl implements ServicesLogin {
    private final LoginDao loginDao;

    @Inject
    public ServicesLoginImpl(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    public Either<String, Login> get(String userName) {
        return loginDao.get(userName);
    }
}
