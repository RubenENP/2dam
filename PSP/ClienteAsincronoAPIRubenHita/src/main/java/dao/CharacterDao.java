package dao;

import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import modelo.characters.Characters;
import modelo.characters.Result;
import java.util.List;

public interface CharacterDao {
    Single<Either<String, List<Result>>> getCharactersByName(String name);
    Single<Either<String, Characters>> getCharactersByType(String type);
    Single<Either<String, List<Result>>> getCharactersByEpisode(int id);
}
