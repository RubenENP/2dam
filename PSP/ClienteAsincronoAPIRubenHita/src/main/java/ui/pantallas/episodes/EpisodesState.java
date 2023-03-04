package ui.pantallas.episodes;

import modelo.episodes.Result;

import java.util.List;

public record EpisodesState(List<Result> listEpisodes, List<String> listCharacters, String error) {
}
