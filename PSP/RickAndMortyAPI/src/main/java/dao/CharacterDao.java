package dao;

import io.vavr.control.Either;
import modelo.characters.Result;
import java.util.List;

public interface CharacterDao {
    Either<String, List<Result>> getCharactersByName(String name);
    Either<String, List<Result>> getCharactersByType(String type);
    Either<String, List<Result>> getCharactersByEpisode(int id);
}
