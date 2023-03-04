package ui.pantallas.episodes;

import javafx.collections.ObservableList;
import modelo.episodes.Result;

public record EpisodesState(ObservableList<Result> listEpisodes, ObservableList<String> listCharacters) {
}
