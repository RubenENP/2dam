package dao.retrofit.llamadas;

import io.reactivex.rxjava3.core.Single;
import modelo.episodes.Episode;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EpisodesAPI {
    @GET("episode")
    Single<Episode> getEpisodesOnePage(@Query("page") int page);
}
