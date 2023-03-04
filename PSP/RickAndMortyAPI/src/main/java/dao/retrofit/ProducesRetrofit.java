package dao.retrofit;

import com.squareup.moshi.Moshi;
import dao.retrofit.llamadas.CharactersAPI;
import dao.retrofit.llamadas.EpisodesAPI;
import dao.retrofit.llamadas.LocationsAPI;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ProducesRetrofit {
    @Produces
    @Singleton
    public Moshi getMoshi()
    {
        return new Moshi.Builder().build();
    }

    @Produces
    @Singleton
    public Retrofit retrofits(Moshi moshi) {
        OkHttpClient clientOK = new OkHttpClient.Builder()
                .connectionPool(new okhttp3.ConnectionPool(1, 2, java.util.concurrent.TimeUnit.SECONDS))
                .build();


        return new Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(clientOK)
                .build();
    }

    @Produces
    public CharactersAPI getCharactersAPI(Retrofit retrofit){
        return retrofit.create(CharactersAPI.class);
    }

    @Produces
    public LocationsAPI getLocationAPI(Retrofit retrofit){return retrofit.create(LocationsAPI.class);}

    @Produces
    public EpisodesAPI getEpisodesAPI(Retrofit retrofit){return retrofit.create(EpisodesAPI.class);}
}
