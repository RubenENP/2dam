package dao.impl;

import dao.EpisodesDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import modelo.episodes.Episode;
import modelo.episodes.Result;
import dao.retrofit.llamadas.EpisodesAPI;
import retrofit2.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EpisodesDaoImpl implements EpisodesDao {
    private final EpisodesAPI episodesAPI;

    @Inject
    EpisodesDaoImpl(EpisodesAPI episodesAPI){
        this.episodesAPI = episodesAPI;
    }

    public Either<String, List<Result>> getAllEpisodes() {
        Either<String, List<Result>> respuesta;

        Response<Episode> r;

        try {
            r = episodesAPI.getEpisodesOnePage(1).execute();

            if (r.isSuccessful()) {
                if (r.body() == null){
                    respuesta = Either.left("ERROR No cargaron los episodios");
                } else {
                    int pages = r.body().info().pages();
                    List<Result> resultList = new ArrayList<>();
                    for (int i = 1; i <= pages; i++){
                        resultList.addAll(Objects.requireNonNull(episodesAPI.getEpisodesOnePage(i).execute().body()).results());
                    }
                    respuesta = Either.right(resultList);
                }
            } else {
                respuesta = Either.left("ERROR No cargaron los episodios");
            }
        } catch (IOException e){
            respuesta = Either.left(e.getMessage());
        }

        return respuesta;
    }

}
