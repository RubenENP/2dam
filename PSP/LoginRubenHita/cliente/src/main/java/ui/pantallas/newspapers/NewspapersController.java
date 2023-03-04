package ui.pantallas.newspapers;

import domain.model.Newspaper;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.common.BasePantallaController;
import ui.common.Pantallas;

public class NewspapersController extends BasePantallaController {
    @FXML
    private TextField nameField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField idField;
    @FXML
    private TableView<Newspaper> newspapersTable;
    @FXML
    private TableColumn<String, Newspaper> idColumn;
    @FXML
    private TableColumn<String, Newspaper> nameColumn;
    @FXML
    private TableColumn<String, Newspaper> releaseDateColumn;

    private final NewspapersViewModel newspapersViewModel;

    @Inject
    NewspapersController(NewspapersViewModel newspapersViewModel) {
        this.newspapersViewModel = newspapersViewModel;
    }

    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name_newspaper"));
        releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("release_date"));

        cambiosEstado();
    }

    private void cambiosEstado() {
        newspapersViewModel.getState().addListener((observableValue, listadoState, stateNew) -> Platform.runLater(() -> {
            if (stateNew.error() != null){
                getPrincipalController().sacarAlertError(stateNew.error());
            }

            if (stateNew.getNewspapers() != null){
                newspapersTable.getItems().clear();
                newspapersTable.getItems().addAll(stateNew.getNewspapers());
            }

            getPrincipalController().root.setCursor(Cursor.DEFAULT);
        }));
    }

    @Override
    public void principalCargado() {
        reload();
    }

    @FXML
    private void atras() {
        getPrincipalController().cargarPantalla(Pantallas.MENU);
    }

    @FXML
    private void buscarPorId() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        newspapersViewModel.getNewspaperById(Integer.parseInt(idField.getText()));
    }

    @FXML
    private void addNewspaper() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        newspapersViewModel.addNewspaper(nameField.getText(), datePicker.getValue());
    }

    @FXML
    private void reload() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        newspapersViewModel.getAllNewspapers();
    }

    public void borrar() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        newspapersViewModel.deleteNewspaper(newspapersTable.getSelectionModel().getSelectedItem().getId());
    }
}
