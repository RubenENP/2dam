package dao.impl;

import com.google.gson.Gson;
import dao.DaoGenerics;
import dao.DaoUser;
import encriptacion.Encriptacion;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import modelo.User;
import okhttp3.Credentials;
import okhttp3.Response;
import retrofit.llamadas.UsersAPI;
import retrofit.network.CacheAuthorization;

import java.security.*;
import java.util.Base64;
import java.util.List;

public class DaoUserImpl extends DaoGenerics implements DaoUser {
    private final UsersAPI usersAPI;

    @Inject
    public DaoUserImpl(UsersAPI usersAPI, Gson gson) {
        super(gson);
        this.usersAPI = usersAPI;
    }

    public Single<Either<String, User>> postUser(User user) {
        return safeAPICall(usersAPI.postUser(Credentials.basic(user.getUsername(), user.getPassword()), user));
    }

    public Single<Either<String, String>> logout(String logout) {
        return safeAPICall(usersAPI.logout(logout));
    }

    public Single<Either<String, User>> login(String user, String pass) {
        return safeAPICall(usersAPI.login(Credentials.basic(user, pass)));
    }

    public Single<Either<String, List<User>>> getAll() {
        return safeAPICall(usersAPI.getUsers());
    }
}
