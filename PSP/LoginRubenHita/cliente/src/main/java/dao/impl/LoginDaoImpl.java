package dao.impl;

import com.google.gson.Gson;
import dao.DaoGenerics;
import dao.LoginDao;
import dao.retrofit.llamadas.LoginAPI;
import domain.model.Usuario;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import network.CacheAuthorization;
import okhttp3.Credentials;

public class LoginDaoImpl extends DaoGenerics implements LoginDao {
    private final LoginAPI loginAPI;
    private CacheAuthorization ca;

    @Inject
    public LoginDaoImpl(Gson gson, LoginAPI loginAPI, CacheAuthorization ca) {
        super(gson);
        this.loginAPI = loginAPI;
        this.ca = ca;
    }

    public Single<Either<String, Usuario>> login(String user, String pass){
        if (ca.getJwt()!=null){
            return Single.create(emitter -> emitter.onSuccess(Either.left("Haz el logout.")));
        }
        return safeAPICall(loginAPI.login(Credentials.basic(user, pass)));
    }

    public Single<Either<String, Usuario>> register(String user, String pass, String email){
        return safeAPICall(loginAPI.register(new Usuario(user, pass, email, 1,"user")));
    }

    public Single<Either<String, String>> cambiarPass(String user){
        return safeAPICall(loginAPI.cambiarPass(user));
    }

    public Single<Either<String, String>> logout(){
        return safeAPICall(loginAPI.logout("logout"));
    }
}
