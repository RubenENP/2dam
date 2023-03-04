package ui.pantallas.characters;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
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
    }

    public void atras() {
        getPrincipalController().cargarPantalla(Pantallas.MENU);
    }

    public void buscar() {
        String name = nameTxt.getText();

        charactersTable.setItems(charactersViewModel.getCharactersByName(name));
    }

    public void buscarPorEspecie() {
        String type = comboBox.getSelectionModel().getSelectedItem();
        charactersTable.setItems(charactersViewModel.getCharactersByType(type));
    }

    public void seleccionCharacter() {
        episodesList.getItems().clear();
        episodesList.getItems().addAll(charactersTable.getSelectionModel().getSelectedItem().getEpisode());

        characterImg.setImage(new Image(charactersTable.getSelectionModel().getSelectedItem().getImage()));
    }
}