package ui.pantallas.episodes;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import modelo.episodes.Result;

import servicios.CharacterServicios;
import servicios.EpisodesServicios;

import java.io.IOException;
import java.util.List;

public class EpisodesViewModel {
    final EpisodesServicios episodesServicios;
    final CharacterServicios characterServicios;

    @Inject
    EpisodesViewModel(EpisodesServicios episodesServicios, CharacterServicios characterServicios) {
        this.episodesServicios = episodesServicios;
        this.characterServicios = characterServicios;

        state = new SimpleObjectProperty<>(new EpisodesState(FXCollections.observableArrayList(), FXCollections.observableArrayList()));
        setState();
    }

    private void setState() {
        Either<String, List<Result>> episodios = episodesServicios.getAllEpisodes();
        if (episodios.isLeft()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(episodios.getLeft());
            a.show();
        }else {
            getState().get().listEpisodes().addAll(episodios.get());
        }
    }

    private final ObjectProperty<EpisodesState> state;

    public ReadOnlyObjectProperty<EpisodesState> getState() {
        return state;
    }

    public ObservableList<Result> getAllEpisodes() {
        return state.get().listEpisodes();
    }

    public ObservableList<String> getCharactersByEpisode(int id) throws IOException {
        state.get().listCharacters().clear();
        Either<String, List<String>> characters = characterServicios.getCharactersByEpisode(id);
        if (characters.isLeft()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(characters.getLeft());
            a.show();
        }else {
            state.get().listCharacters().addAll(characters.get());
        }

        return state.get().listCharacters();
    }
}
