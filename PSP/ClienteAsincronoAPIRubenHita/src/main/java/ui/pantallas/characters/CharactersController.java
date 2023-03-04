package ui.pantallas.characters;

import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.extern.log4j.Log4j2;
import modelo.characters.Result;
import ui.common.BasePantallaController;
import ui.common.Pantallas;

import java.util.List;

@Log4j2
public class CharactersController extends BasePantallaController {

    @FXML
    private ListView<String> episodesList;
    @FXML
    private ImageView characterImg;
    @FXML
    private TableColumn<String, Result> columnSpecies;
    @FXML
    private TableColumn<String, Result> columnType;
    @FXML
    private TableColumn<String, Result> columnGender;
    @FXML
    private TableColumn<String, Result> columnStatus;
    @FXML
    private TableColumn<String, Result> columnName;
    @FXML
    private TableView<Result> charactersTable;
    @FXML
    private TextField nameTxt;

    @FXML
    private ComboBox<String> comboBox;

    final CharactersViewModel charactersViewModel;

    @Inject
    CharactersController(CharactersViewModel charactersViewModel){
        this.charactersViewModel = charactersViewModel;
    }

    public void initialize(){
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        columnSpecies.setCellValueFactory(new PropertyValueFactory<>("species"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        comboBox.getItems().addAll(List.of("Human", "Alien"));

        cambiosEstado();
    }

    private void cambiosEstado() {
        charactersViewModel.getState().addListener((observableValue, listadoState, listadoStateNew) -> Platform.runLater(() -> {
            if (listadoStateNew.error() != null) {
                getPrincipalController().sacarAlertError(listadoStateNew.error());
            }
            if (listadoStateNew.getCharacters() != null) {
                charactersTable.getItems().clear();
                charactersTable.getItems().addAll(listadoStateNew.getCharacters());
            }

            getPrincipalController().root.setCursor(Cursor.DEFAULT);
        }));
    }

    public void atras() {
        getPrincipalController().root.setCursor(Cursor.DEFAULT);
        getPrincipalController().cargarPantalla(Pantallas.MENU);
    }

    public void buscar() {
        getPrincipalController().root.setCursor(Cursor.WAIT);

        String name = nameTxt.getText();

        charactersViewModel.getCharactersByName(name);
    }

    public void buscarPorEspecie() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        String type = comboBox.getSelectionModel().getSelectedItem();

        charactersViewModel.getCharactersByType(type);

    }

    public void seleccionCharacter() {
        episodesList.getItems().clear();
        episodesList.getItems().addAll(charactersTable.getSelectionModel().getSelectedItem().getEpisode());

        characterImg.setImage(new Image(charactersTable.getSelectionModel().getSelectedItem().getImage()));
    }
}