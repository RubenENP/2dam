package servicios.impl;

import dao.EpisodesDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import modelo.episodes.Result;
import servicios.EpisodesServicios;

import java.io.IOException;
import java.util.List;

public class EpisodiesServiciosImpl implements EpisodesServicios {
    final EpisodesDao episodesDao;

    @Inject
    EpisodiesServiciosImpl(EpisodesDao episodesDao){
        this.episodesDao = episodesDao;
    }

    public Either<String, List<Result>> getAllEpisodes() {
        Either<String, List<Result>> respuesta;

        try {
            Either<String, List<Result>> episodios = episodesDao.getAllEpisodes();
            if(episodios.isLeft()){
                respuesta = Either.left("No han podido cargar los episodios");
            }else {
                respuesta = Either.right(episodios.get());
            }
        } catch (IOException e){
            return Either.left(e.getMessage());
        }

        return respuesta;
    }
}
