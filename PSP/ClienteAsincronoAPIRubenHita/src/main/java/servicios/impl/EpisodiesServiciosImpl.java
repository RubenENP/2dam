package servicios.impl;

import dao.EpisodesDao;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import modelo.episodes.Result;
import servicios.EpisodesServicios;

import java.util.List;

public class EpisodiesServiciosImpl implements EpisodesServicios {
    final EpisodesDao episodesDao;

    @Inject
    EpisodiesServiciosImpl(EpisodesDao episodesDao){
        this.episodesDao = episodesDao;
    }

    public Single<Either<String, List<Result>>> getAllEpisodes() {
        return episodesDao.getAllEpisodes();
    }
}
