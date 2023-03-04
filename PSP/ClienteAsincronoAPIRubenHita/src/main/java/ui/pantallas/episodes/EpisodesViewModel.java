package ui.pantallas.episodes;

import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;

import servicios.CharacterServicios;
import servicios.EpisodesServicios;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class EpisodesViewModel {
    final EpisodesServicios episodesServicios;
    final CharacterServicios characterServicios;

    @Inject
    EpisodesViewModel(EpisodesServicios episodesServicios, CharacterServicios characterServicios) {
        this.episodesServicios = episodesServicios;
        this.characterServicios = characterServicios;

        state = new SimpleObjectProperty<>(new EpisodesState(FXCollections.observableArrayList(), FXCollections.observableArrayList(), null));
    }

    private final ObjectProperty<EpisodesState> state;

    public ReadOnlyObjectProperty<EpisodesState> getState() {
        return state;
    }

    public void getAllEpisodes() throws IOException {
        episodesServicios.getAllEpisodes()
                .delay(5, TimeUnit.SECONDS)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    EpisodesState episodesState;

                    if (either.isLeft()) {
                        episodesState = new EpisodesState(null, null, either.getLeft());
                    }else {
                        episodesState = new EpisodesState(either.get(), null, null);
                    }

                    state.setValue(episodesState);
                });
    }

    public void getCharactersByEpisode(int id) {
        characterServicios.getCharactersByEpisode(id)
                .delay(5, TimeUnit.SECONDS)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    EpisodesState episodesState;

                    if (either.isLeft()) {
                        episodesState = new EpisodesState(null, null, either.getLeft());
                    } else {
                        episodesState = new EpisodesState(state.get().listEpisodes(),
                                either.get().stream().map(modelo.characters.Result::getName).toList(), null);
                    }

                    state.setValue(episodesState);
                });
    }
}
