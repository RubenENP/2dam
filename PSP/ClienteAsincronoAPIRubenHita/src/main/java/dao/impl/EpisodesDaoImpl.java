package dao.impl;

import dao.EpisodesDao;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import modelo.episodes.Result;
import dao.retrofit.llamadas.EpisodesAPI;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

import java.util.List;
import java.util.Objects;

public class EpisodesDaoImpl implements EpisodesDao {
    private final EpisodesAPI episodesAPI;

    @Inject
    EpisodesDaoImpl(EpisodesAPI episodesAPI){
        this.episodesAPI = episodesAPI;
    }

    public Single<Either<String, List<Result>>> getAllEpisodes() {
        return episodesAPI.getEpisodesOnePage(1)
                .map(rj -> Either.right(rj.results()).mapLeft(Objects::toString))
                .subscribeOn(Schedulers.io())
                .onErrorReturn(throwable -> {
                    Either<String, List<Result>> error = Either.left("Error de comunicacion");
                    if (throwable instanceof HttpException httpException){
                        try (ResponseBody errorBody = Objects.requireNonNull(httpException.response()).errorBody()) {
                            assert errorBody != null;
                            if (!Objects.equals(errorBody.contentType(), MediaType.get("application/json"))) {
                                error = Either.left("Error de comunicacion " + Objects.requireNonNull(httpException.response()).message());
                            }
                        }
                    }

                    return error;
                });
    }

}
