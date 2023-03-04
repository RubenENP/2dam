package retrofit;

import modelo.pokemons.Pokemons;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokemonAPI {
    @GET("Any")
    Call<Pokemons> getAnyPokemon(@Query("count") int count);
}
