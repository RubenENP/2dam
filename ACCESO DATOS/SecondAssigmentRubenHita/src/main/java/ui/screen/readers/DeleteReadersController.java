package ui.screen.readers;

import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.reader.Reader;
import services.ServicesReaders;
import ui.common.BaseScreenController;

import java.io.IOException;

public class DeleteReadersController extends BaseScreenController {
    @FXML
    private TableView<Reader> tableReaders;
    @FXML
    private TableColumn<String, Reader> columnId;
    @FXML
    private TableColumn<String, Reader> columnName;
    @FXML
    private TableColumn<String, Reader> columnBirthDate;

    ServicesReaders servicesReaders;

    @Inject
    DeleteReadersController(ServicesReaders servicesReaders) {
        this.servicesReaders = servicesReaders;
    }

    public void initialize(){
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    }

    private void showAlert(String m, Alert.AlertType alertType) {
        Alert a = new Alert(alertType);
        a.setContentText(m);
        a.show();
    }

    public void delete() {
        int id = tableReaders.getSelectionModel().getSelectedItem().getId();
        showAlert("Rows affected: "+servicesReaders.delete(id), Alert.AlertType.INFORMATION);
    }
}
