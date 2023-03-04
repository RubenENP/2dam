package dao.retrofit;

import com.google.gson.Gson;
import com.squareup.moshi.Moshi;
import common.Constantes;
import dao.retrofit.llamadas.*;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import network.AuthorizationInterceptor;
import network.CacheAuthorization;
import network.JavaNetCookieJar;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
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
    private final CacheAuthorization cacheAuthorization;

    @Inject
    public ProducesRetrofit(CacheAuthorization cacheAuthorization) {
        this.cacheAuthorization = cacheAuthorization;
    }

    @Produces
    @Singleton
    public Moshi getMoshi() {
        return new Moshi.Builder().build();
    }

    @Produces
    @Singleton
    public Retrofit retrofits(Gson gson) {
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
    public NewspapersAPI getNewspapersAPI(Retrofit retrofit) {
        return retrofit.create(NewspapersAPI.class);
    }

    @Produces
    public ArticlesAPI getArticlesAPI(Retrofit retrofit) {
        return retrofit.create(ArticlesAPI.class);
    }

    @Produces
    public ArticleTypeAPI getArticleTypeAPI(Retrofit retrofit) {
        return retrofit.create(ArticleTypeAPI.class);
    }

    @Produces
    public ReadersAPI getReadersAPI(Retrofit retrofit) {
        return retrofit.create(ReadersAPI.class);
    }

    @Produces
    public LoginAPI loginAPI(Retrofit retrofit) {
        return retrofit.create(LoginAPI.class);
    }
}
