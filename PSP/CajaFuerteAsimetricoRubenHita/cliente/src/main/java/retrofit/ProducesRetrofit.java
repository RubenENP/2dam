package retrofit;

import com.google.gson.Gson;
import com.squareup.moshi.Moshi;
import common.Constantes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit.llamadas.CarpetaAPI;
import retrofit.llamadas.MensajeAPI;
import retrofit.llamadas.SecurityAPI;
import retrofit.llamadas.UsersAPI;
import retrofit.network.AuthorizationInterceptor;
import retrofit.network.CacheAuthorization;
import retrofit.network.JavaNetCookieJar;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class ProducesRetrofit {

    @Produces
    @Singleton
    public Moshi getMoshi() {
        return new Moshi.Builder().build();
    }

    @Produces
    @Singleton
    public Retrofit retrofits(Gson gson, CacheAuthorization cacheAuthorization) {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

        OkHttpClient clientOK = new OkHttpClient.Builder()
                .readTimeout(Duration.of(10, ChronoUnit.MINUTES))
                .callTimeout(Duration.of(10, ChronoUnit.MINUTES))
                .connectTimeout(Duration.of(10, ChronoUnit.MINUTES))
                //.addInterceptor(new AuthorizationInterceptor(cache))
                .connectionPool(new ConnectionPool(1, 1, TimeUnit.SECONDS))
                .addInterceptor(new AuthorizationInterceptor(cacheAuthorization))
                // necesario para la sesion
                .cookieJar(new JavaNetCookieJar(cookieManager))
                .build();


        return new Retrofit.Builder()
                .baseUrl(Constantes.URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(clientOK)
                .build();
    }

    @Produces
    public UsersAPI usersAPI(Retrofit retrofit) {
        return retrofit.create(UsersAPI.class);
    }

    @Produces
    public CarpetaAPI carpetaAPI(Retrofit retrofit) {
        return retrofit.create(CarpetaAPI.class);
    }

    @Produces
    public MensajeAPI mensajeAPI(Retrofit retrofit) {
        return retrofit.create(MensajeAPI.class);
    }

    @Produces
    public SecurityAPI securityAPI(Retrofit retrofit) {return retrofit.create(SecurityAPI.class);}
}
