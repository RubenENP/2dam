package servicios;

import io.vavr.control.Either;
import modelo.episodes.Result;

import java.util.List;

public interface EpisodesServicios {
    Either<String, List<Result>> getAllEpisodes();
}
