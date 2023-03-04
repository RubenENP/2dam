package servicios;

import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import modelo.episodes.Result;

import java.io.IOException;
import java.util.List;

public interface EpisodesServicios {
    Single<Either<String, List<Result>>> getAllEpisodes() throws IOException;
}
