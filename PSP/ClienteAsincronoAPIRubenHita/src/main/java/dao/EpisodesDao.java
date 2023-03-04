package dao;

import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import modelo.episodes.Result;

import java.util.List;

public interface EpisodesDao {
    Single<Either<String, List<Result>>> getAllEpisodes();
}
