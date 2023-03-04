package dao.retrofit;

import com.google.gson.Gson;
import com.squareup.moshi.Moshi;
import common.Constantes;
import dao.retrofit.llamadas.ArticleTypeAPI;
import dao.retrofit.llamadas.ArticlesAPI;
import dao.retrofit.llamadas.NewspapersAPI;
import dao.retrofit.llamadas.ReadersAPI;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProducesRetrofit {

    @Produces
    @Singleton
    public Moshi getMoshi() {
        return new Moshi.Builder().build();
    }

    @Produces
    @Singleton
    public Retrofit retrofits(Gson gson) {
        OkHttpClient clientOK = new OkHttpClient.Builder()
                .connectionPool(new okhttp3.ConnectionPool(1, 2, java.util.concurrent.TimeUnit.SECONDS))
                .build();


        return new Retrofit.Builder()
                .baseUrl(Constantes.HTTP_LOCALHOST_8080_SERVER_1_0_SNAPSHOT_API)
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
    public ArticlesAPI getArticlesAPI(Retrofit retrofit){return retrofit.create(ArticlesAPI.class);}

    @Produces
    public ArticleTypeAPI getArticleTypeAPI(Retrofit retrofit){return  retrofit.create(ArticleTypeAPI.class);}

    @Produces
    public ReadersAPI getReadersAPI(Retrofit retrofit){return  retrofit.create(ReadersAPI.class);}
}
