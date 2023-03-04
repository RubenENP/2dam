package ui.pantallas.episodes;

import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.episodes.Result;
import ui.common.BasePantallaController;
import ui.common.Pantallas;

import java.io.IOException;


public class EpisodesController extends BasePantallaController {
    @FXML
    private ListView<String> charactersList;
    @FXML
    private TableView<Result> episodesTable;
    @FXML
    private TableColumn<String, Result> idColumn;
    @FXML
    private TableColumn<String, Result> titleColumn;
    @FXML
    private TableColumn<String, Result> fechaColumn;
    @FXML
    private TableColumn<String, Result> episodioColumn;

    final EpisodesViewModel episodesViewModel;
    @Inject
    EpisodesController(EpisodesViewModel episodesViewModel){
        this.episodesViewModel = episodesViewModel;
    }

    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("air_date"));
        episodioColumn.setCellValueFactory(new PropertyValueFactory<>("episode"));

        cambiosEstado();
    }

    private void cambiosEstado() {
        episodesViewModel.getState().addListener((observableValue, listadoState, listadoStateNew) -> Platform.runLater(() -> {
            if (listadoStateNew.error() != null) {
                getPrincipalController().sacarAlertError(listadoStateNew.error());
            }
            if (listadoStateNew.listEpisodes() != null) {
                episodesTable.getItems().clear();
                episodesTable.getItems().addAll(listadoStateNew.listEpisodes());
            }
            if(listadoStateNew.listCharacters() != null){
                charactersList.getItems().clear();
                charactersList.getItems().addAll(listadoStateNew.listCharacters());
            }

            getPrincipalController().root.setCursor(Cursor.DEFAULT);
        }));
    }

    @Override
    public void principalCargado() throws IOException {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        episodesViewModel.getAllEpisodes();
    }

    @FXML
    private void atras() {
        getPrincipalController().cargarPantalla(Pantallas.MENU);
    }
    @FXML
    public void showCharacters() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        int id = episodesTable.getSelectionModel().getSelectedItem().getId();
        episodesViewModel.getCharactersByEpisode(id);
    }
}
