package dao;

import io.vavr.control.Either;
import modelo.episodes.Result;

import java.io.IOException;
import java.util.List;

public interface EpisodesDao {
    Either<String, List<Result>> getAllEpisodes() throws IOException;
}
