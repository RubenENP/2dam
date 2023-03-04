package ui.screen.readers;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.reader.Reader;
import services.ServicesReaders;
import ui.common.BaseScreenController;

public class AddReaderController extends BaseScreenController {
    @FXML
    private TextField idText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField birthDateText;
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
    AddReaderController(ServicesReaders servicesReaders) {
        this.servicesReaders = servicesReaders;
    }

    public void initialize() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    }

    public void addReader() {
        int rowNum = servicesReaders.addReader(Integer.parseInt(idText.getText()), nameText.getText(), birthDateText.getText());

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(rowNum + " rows saved");
        a.show();
    }
}
