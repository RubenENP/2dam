package services.impl;

import dao.DaoUser;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import modelo.User;
import services.ServicesUser;

import java.util.List;

public class ServicesUserImpl implements ServicesUser {
    private final DaoUser daoUser;

    @Inject
    public ServicesUserImpl(DaoUser daoUser) {
        this.daoUser = daoUser;
    }

    public Single<Either<String, User>> postUser(User user){
        return daoUser.postUser(user);
    }

    public Single<Either<String, String>> logout(String logout){
        return daoUser.logout(logout);
    }
    public Single<Either<String, User>> login(String user, String pass){
        return daoUser.login(user, pass);
    }

    public Single<Either<String, List<User>>> getAll(){
        return daoUser.getAll();
    }
}
