package ui.pantallas.readers;

import domain.model.reader.Reader;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.common.BasePantallaController;
import ui.common.Pantallas;
import ui.pantallas.newspapers.NewspapersViewModel;

public class ReadersController extends BasePantallaController {
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private TableView<Reader> readersTable;
    @FXML
    private TableColumn<String, Reader> idColumn;
    @FXML
    private TableColumn<String, Reader> nameColumn;
    @FXML
    private TableColumn<String, Reader> birthDateColumn;
    @FXML
    private TextField nameField;
    @FXML
    private TextField idField;

    private final ReadersViewModel readersViewModel;

    @Inject
    ReadersController(ReadersViewModel readersViewModel) {
        this.readersViewModel = readersViewModel;
    }

    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

        cambiosEstado();
    }

    private void cambiosEstado() {
        readersViewModel.getState().addListener((observableValue, listadoState, stateNew) -> Platform.runLater(() -> {
            if (stateNew.error() != null){
                getPrincipalController().sacarAlertError(stateNew.error());
            }

            if (stateNew.readerList() != null){
                readersTable.getItems().clear();
                readersTable.getItems().addAll(stateNew.readerList());
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
        readersViewModel.getReaderById(Integer.parseInt(idField.getText()));
    }

    @FXML
    private void addReader() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        readersViewModel.addReader(nameField.getText(), birthDatePicker.getValue());
    }

    @FXML
    private void reload() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        readersViewModel.getAllReaders();
    }

    @FXML
    private void update() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        readersViewModel.updateReader(readersTable.getSelectionModel().getSelectedItem().getId(), nameField.getText(), birthDatePicker.getValue());
    }
}
