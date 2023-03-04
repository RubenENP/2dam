package ui.screen.readers;

import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ReaderHib;
import services.ServicesReaders;
import ui.common.BaseScreenController;

import java.io.IOException;

public class AddReaderController extends BaseScreenController {
    @FXML
    private TextField nameText;
    @FXML
    private DatePicker birthDateText;
    @FXML
    private TableView<ReaderHib> tableReaders;
    @FXML
    private TableColumn<String, ReaderHib> columnId;
    @FXML
    private TableColumn<String, ReaderHib> columnName;
    @FXML
    private TableColumn<String, ReaderHib> columnBirthDate;
    ServicesReaders servicesReaders;

    @Inject
    AddReaderController(ServicesReaders servicesReaders) {
        this.servicesReaders = servicesReaders;
    }

    public void initialize() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("nameReader"));
        columnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthReader"));
    }

    @Override
    public void principalCargado() throws IOException, JAXBException {
        tableReaders.getItems().addAll(servicesReaders.getAll().get());
    }

    public void addReader() {
        servicesReaders.addReader(0, nameText.getText(), birthDateText.getValue().toString());
        tableReaders.getItems().clear();
        tableReaders.getItems().addAll(servicesReaders.getAll().get());
    }
}
