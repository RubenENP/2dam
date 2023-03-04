package services.impl;

import config.ConfigYaml;
import dao.DaoLogin;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Login;
import services.ServicesLogin;

import java.util.List;

public class ServicesLoginImpl implements ServicesLogin {
    DaoLogin daoLogin;

    @Inject
    ServicesLoginImpl(DaoLogin daoLogin) {
        this.daoLogin = daoLogin;
    }

    public boolean loginAdmin(String password){
        return password.equals(ConfigYaml.getInstance().getPasswd());
    }

    public Either<Integer, String> loginUser(String user, String password) {
        Either<Integer, String> response;

        Either<List<Login>, String> allLogin = daoLogin.getAll();

        if (allLogin.isLeft()){
            Login userWithName = allLogin.getLeft().stream().filter(login -> login.getUser().equals(user))
                    .findFirst().orElse(null);

            if (userWithName != null){
                if (userWithName.getPassword().equals(password)){
                    response = Either.left(userWithName.getIdReader());
                } else {
                    response = Either.right("Incorrect password");
                }
            } else {
                response = Either.right("Incorrect user name");
            }
        } else {
            response = Either.right(allLogin.get());
        }

        return response;
    }
}
