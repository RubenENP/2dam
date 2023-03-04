package ui.pantallas.episodes;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
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
    }

    @Override
    public void principalCargado(){
        episodesTable.setItems(episodesViewModel.getAllEpisodes());
    }

    @FXML
    private void atras() {
        getPrincipalController().cargarPantalla(Pantallas.MENU);
    }
    @FXML
    public void showCharacters() throws IOException {
        int id = episodesTable.getSelectionModel().getSelectedItem().getId();
        charactersList.setItems(episodesViewModel.getCharactersByEpisode(id));
    }
}
