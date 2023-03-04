package ui.pantallas.characters;

import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import servicios.CharacterServicios;

import java.util.concurrent.TimeUnit;

public class CharactersViewModel {
    final CharacterServicios characterServicios;

    @Inject
    public CharactersViewModel(CharacterServicios characterServicios){
        this.characterServicios = characterServicios;

        state = new SimpleObjectProperty<>(new CharactersState(FXCollections.observableArrayList(), null));
    }

    private final ObjectProperty<CharactersState> state;

    public ReadOnlyObjectProperty<CharactersState> getState() {
        return state;
    }

    public void getCharactersByName(String name) {
        characterServicios.getCharactersByName(name)
                .delay(5, TimeUnit.SECONDS)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    CharactersState charactersState;

                    if(either.isLeft()){
                        charactersState = new CharactersState(null, either.getLeft());
                    } else {
                        charactersState = new CharactersState(either.get(), null);
                    }

                    state.setValue(charactersState);
                });
    }

    public void getCharactersByType(String type) {
        characterServicios.getCharactersByType(type)
                .delay(5, TimeUnit.SECONDS)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    CharactersState charactersState;

                    if(either.isLeft()){
                        charactersState = new CharactersState(null, either.getLeft());
                    } else {
                        charactersState = new CharactersState(either.get().getResults(), null);
                    }

                    state.setValue(charactersState);
                });
    }
}
