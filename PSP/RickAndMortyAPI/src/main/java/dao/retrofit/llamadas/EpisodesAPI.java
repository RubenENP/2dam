package dao.retrofit.llamadas;

import modelo.episodes.Episode;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EpisodesAPI {
    @GET("episode")
    Call<Episode> getEpisodesOnePage(@Query("page") int page);
}
