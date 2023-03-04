package dao.retrofit.llamadas;

import modelo.characters.Characters;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CharactersAPI {
    @GET("character")
    Call<Characters> getCharacterByName(@Query("name") String name);

    @GET("character")
    Call<Characters> getCharacterByType(@Query("species") String type, @Query("page") int page);

    @GET("character")
    Call<Characters> getCharacterByPage(@Query("page") int page);
}
