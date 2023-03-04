package ui.pantallas.characters;

import javafx.collections.ObservableList;
import modelo.characters.Result;

public record CharactersState(ObservableList<Result> getCharacters) {
}
