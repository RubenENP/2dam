package ui;

import com.squareup.moshi.Moshi;
import modelo.pokemons.Pokemons;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit.PokemonAPI;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Moshi moshi = new Moshi.Builder().build();

        OkHttpClient clientOK = new OkHttpClient.Builder()
                .connectionPool(new okhttp3.ConnectionPool(1, 2, java.util.concurrent.TimeUnit.SECONDS))
                .build();


        System.out.println(clientOK.newCall(new Request.Builder().url("https://pokeapi.co/api/v2/pokemon/").build()).execute().body().string());


        Retrofit retro = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/pokemon/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(clientOK)
                .build();

        PokemonAPI pokemons = retro.create(PokemonAPI.class);

        Response<Pokemons> r = pokemons.getAnyPokemon(1154).execute();


        System.out.println(r.body());

    }
}