package network;

import common.Constantes;
import jakarta.inject.Inject;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class AuthorizationInterceptor implements Interceptor {
    private CacheAuthorization ca;

    @Inject
    public AuthorizationInterceptor(CacheAuthorization cacheAuthorization) {
        this.ca = cacheAuthorization;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request newRequest;
        Response response;
        if (request.header("Logout") != null) {
            ca.setUser(null);
            ca.setPass(null);
            ca.setJwt(null);
        }
        if (ca.getJwt() != null) {
            newRequest = request.newBuilder()
                    .header(Constantes.AUTHORIZATION, ca.getJwt()).build();
        } else {
            newRequest = request;
        }

        response = chain.proceed(newRequest);
        if (response.header(Constantes.AUTHORIZATION) != null) {
            ca.setJwt(response.header(Constantes.AUTHORIZATION));
        }

        return response;
    }
}
