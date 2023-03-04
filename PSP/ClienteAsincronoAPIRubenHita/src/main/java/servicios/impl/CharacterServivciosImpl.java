package servicios.impl;

import dao.CharacterDao;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import modelo.characters.Characters;
import modelo.characters.Result;
import servicios.CharacterServicios;

import java.util.List;

public class CharacterServivciosImpl implements CharacterServicios {
    final CharacterDao characterDao;

    @Inject
    CharacterServivciosImpl(CharacterDao characterDao){
        this.characterDao = characterDao;
    }

    public Single<Either<String, List<Result>>> getCharactersByName(String name) {
        return characterDao.getCharactersByName(name);
    }

    public Single<Either<String, Characters>> getCharactersByType(String type) {
        return characterDao.getCharactersByType(type);
    }

    public Single<Either<String, List<Result>>> getCharactersByEpisode(int id) {
        return characterDao.getCharactersByEpisode(id);
    }
}
