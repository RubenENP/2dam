package ui.pantallas.characters;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import modelo.characters.Result;
import servicios.CharacterServicios;

import java.io.IOException;
import java.util.List;

public class CharactersViewModel {
    final CharacterServicios characterServicios;

    @Inject
    public CharactersViewModel(CharacterServicios characterServicios){
        this.characterServicios = characterServicios;

        state = new SimpleObjectProperty<>(new CharactersState(FXCollections.observableArrayList()));
    }

    private final ObjectProperty<CharactersState> state;

    public ReadOnlyObjectProperty<CharactersState> getState() {
        return state;
    }

    public ObservableList<Result> getCharactersByName(String name) {
        Either<String, List<Result>> getCharacters = characterServicios.getCharactersByName(name);

        getState().get().getCharacters().clear();

        if (getCharacters.isLeft()){
            alerta(getCharacters.getLeft());
        }else {
            getState().get().getCharacters().addAll(getCharacters.get().stream().toList());
        }

        return getState().get().getCharacters();
    }

    public ObservableList<Result> getCharactersByType(String type) {
        Either<String, List<Result>> getCharacters = null;

        getState().get().getCharacters().clear();

        try {
            getCharacters = characterServicios.getCharactersByType(type);

            if (getCharacters.isLeft()){
                alerta(getCharacters.getLeft());
            }else {
                getState().get().getCharacters().addAll(characterServicios.getCharactersByType(type).get().stream().toList());
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return getState().get().getCharacters();
    }

    private static void alerta(String mensaje){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(mensaje);
        a.show();
    }
}
