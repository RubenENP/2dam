package dao.impl;


import dao.CharacterDao;
import dao.EpisodesDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import modelo.characters.Characters;
import modelo.characters.Result;
import dao.retrofit.llamadas.CharactersAPI;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CharacterDaoImpl implements CharacterDao {
    final EpisodesDao episodesDao;
    private final CharactersAPI charactersAPI;
    @Inject
    CharacterDaoImpl(EpisodesDao episodesDao, CharactersAPI charactersAPI){
        this.episodesDao = episodesDao;
        this.charactersAPI = charactersAPI;
    }
    public Single<Either<String, List<Result>>> getCharactersByName(String name)  {
        return charactersAPI.getCharacterByName(name)
                .map(rj -> {
                    List<Result> resultList = rj.getResults();
                    return Either.right(resultList).mapLeft(Objects::toString);
                })
                .subscribeOn(Schedulers.io())
                .onErrorReturn(throwable -> {
                    Either<String, List<Result>> error = Either.left("Error de comunicacion");
                    if (throwable instanceof HttpException httpException){
                        try (ResponseBody errorBody = Objects.requireNonNull(httpException.response()).errorBody()) {
                            if (!Objects.equals(Objects.requireNonNull(errorBody).contentType(), MediaType.get("application/json"))) {
                                error = Either.left("Error de comunicacion " + Objects.requireNonNull(httpException.response()).message());
                            }
                        }
                    }

                    return error;
                });
    }

    public Single<Either<String, Characters>> getCharactersByType(String type) {
        return charactersAPI.getCharacterByType(type, 1)
                .map(rj -> Either.right(rj).mapLeft(Objects::toString))
                .subscribeOn(Schedulers.io())
                .onErrorReturn(throwable -> {
                    Either<String, Characters> error = Either.left("Error de comunicacion");
                    if (throwable instanceof HttpException httpException){
                        try (ResponseBody errorBody = Objects.requireNonNull(httpException.response()).errorBody()) {
                            if (!Objects.equals(Objects.requireNonNull(errorBody).contentType(), MediaType.get("application/json"))) {
                                error = Either.left("Error " + Objects.requireNonNull(httpException.response()).message());
                            }
                        }
                    }

                    return error;
                });
    }
    public Single<Either<String, List<Result>>> getCharactersByEpisode(int id) {
        return charactersAPI.getCharacterByPage(1)
                .map(rj -> {
                    Either<String, List<modelo.episodes.Result>> listaEpisodios = episodesDao.getAllEpisodes().blockingGet();
                    List<Result> resultList = new ArrayList<>();

                    modelo.episodes.Result episodio = listaEpisodios.get().stream()
                            .filter(result -> result.getId() == id).findFirst().orElse(null);

                    rj.getResults().forEach(result -> result.getEpisode()
                            .forEach(episode -> {
                                if(Objects.equals(episode, Objects.requireNonNull(episodio).getUrl())){
                                    resultList.add(result);
                                }
                            }));

                    return Either.right(resultList).mapLeft(Objects::toString);
                })
                .subscribeOn(Schedulers.io())
                .onErrorReturn(throwable -> {
                    Either<String, List<Result>> error = Either.left("Error de comunicacion");
                    if (throwable instanceof HttpException httpException){
                        try (ResponseBody errorBody = Objects.requireNonNull(httpException.response()).errorBody()) {
                            if (!Objects.equals(Objects.requireNonNull(errorBody).contentType(), MediaType.get("application/json"))) {
                                error = Either.left("Error " + Objects.requireNonNull(httpException.response()).message());
                            }
                        }
                    }

                    return error;
                });
    }
}
