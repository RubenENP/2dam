package ui.screen.readers;

import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.reader.Reader;
import services.ServicesReaders;
import ui.common.BaseScreenController;

import java.io.IOException;
import java.time.LocalDate;

public class UpdateReadersController extends BaseScreenController {
    public TextField newNameTxt;
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
    UpdateReadersController(ServicesReaders servicesReaders) {
        this.servicesReaders = servicesReaders;
    }
    public void initialize(){
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    }

    private void showAlert(String m, Alert.AlertType alertType){
        Alert a = new Alert(alertType);
        a.setContentText(m);
        a.show();
    }

    public void setName() {
        showAlert("Rows Affected: "+servicesReaders.update(newNameTxt.getText(), tableReaders.getSelectionModel().getSelectedItem().getId(), LocalDate.now().toString()),
                Alert.AlertType.INFORMATION);
    }
}
