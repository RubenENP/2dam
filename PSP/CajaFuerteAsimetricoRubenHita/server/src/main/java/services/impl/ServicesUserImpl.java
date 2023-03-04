package services.impl;

import dao.UserDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import modelo.User;
import services.ServicesUser;

import java.util.List;

public class ServicesUserImpl implements ServicesUser {
    private final UserDao userDao;

    @Inject
    public ServicesUserImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public Either<String, User> register(User u) {
        return userDao.register(u);
    }

    @Override
    public Either<String, User> login(User u){
        return userDao.login(u);
    }

    @Override
    public Either<String, User> comprobarUsuario(String caller){
        return userDao.comprobarUsuario(caller);
    }
}
