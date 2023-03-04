package dao.retrofit.llamadas;

import io.reactivex.rxjava3.core.Single;
import modelo.characters.Characters;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CharactersAPI {
    @GET("character")
    Single<Characters> getCharacterByName(@Query("name") String name);

    @GET("character")
    Single<Characters> getCharacterByType(@Query("species") String type, @Query("page") int page);

    @GET("character")
    Single<Characters> getCharacterByPage(@Query("page") int page);
}
