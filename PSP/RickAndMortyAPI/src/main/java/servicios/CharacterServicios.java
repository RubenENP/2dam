package servicios;

import io.vavr.control.Either;
import modelo.characters.Result;

import java.io.IOException;
import java.util.List;

public interface CharacterServicios {
    Either<String, List<Result>> getCharactersByName(String name);

    Either<String, List<Result>> getCharactersByType(String type) throws IOException;

    Either<String, List<String>> getCharactersByEpisode(int id) throws IOException;
}
