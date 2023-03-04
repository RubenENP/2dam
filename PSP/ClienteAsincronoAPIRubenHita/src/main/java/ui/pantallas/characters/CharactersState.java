package ui.pantallas.characters;

import modelo.characters.Result;

import java.util.List;

public record CharactersState(List<Result> getCharacters, String error) {
}
