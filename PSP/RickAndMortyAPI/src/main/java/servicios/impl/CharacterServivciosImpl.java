package servicios.impl;

import dao.CharacterDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import modelo.characters.Result;
import servicios.CharacterServicios;

import java.util.List;

public class CharacterServivciosImpl implements CharacterServicios {
    final CharacterDao characterDao;

    @Inject
    CharacterServivciosImpl(CharacterDao characterDao){
        this.characterDao = characterDao;
    }

    public Either<String, List<Result>> getCharactersByName(String name) {
        Either<String, List<Result>> respuesta;

        Either<String, List<Result>> pregunta = characterDao.getCharactersByName(name);
        if (pregunta.isLeft()){
            respuesta = Either.left(pregunta.getLeft());
        }else {
            respuesta = Either.right(pregunta).get();
        }

        return respuesta;
    }

    public Either<String, List<Result>> getCharactersByType(String type) {
        Either<String, List<Result>> respuesta;

        Either<String, List<Result>> characters = characterDao.getCharactersByType(type);
        if (characters.isLeft()){
            respuesta = Either.left(characters.getLeft());
        }else {
            respuesta = Either.right(characters.get());
        }

        return respuesta;
    }

    public Either<String, List<String>> getCharactersByEpisode(int id) {
        Either<String, List<String>> respuesta;
        Either<String, List<Result>> characters = characterDao.getCharactersByEpisode(id);

        if (characters.isLeft()){
            respuesta = Either.left(characters.getLeft());
        }else {
            respuesta = Either.right(characters.get().stream().map(modelo.characters.Result::getName).toList());
        }

        return respuesta;
    }
}
