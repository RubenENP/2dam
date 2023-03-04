package ui.screens.readers;

import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Newspaper;
import model.reader.Reader;
import services.ServicesArticleType;
import services.ServicesNewspaper;
import services.ServicesReaders;
import ui.common.BaseScreenController;

import java.io.IOException;

public class ShowReadersController extends BaseScreenController {
    @FXML
    private ComboBox<String> comboNewspapers;
    @FXML
    private ComboBox<String> comboArticleType;
    @FXML
    private TableView<Reader> tableReaders;
    @FXML
    private TableColumn<String, Reader> columnId;
    @FXML
    private TableColumn<String, Reader> columnName;
    @FXML
    private TableColumn<String, Reader> columnBirthDate;

    ServicesReaders servicesReaders;
    ServicesArticleType servicesArticleType;
    ServicesNewspaper servicesNewspaper;

    @Inject
    ShowReadersController(ServicesReaders servicesReaders, ServicesArticleType servicesArticleType,
                          ServicesNewspaper servicesNewspaper){
        this.servicesReaders = servicesReaders;
        this.servicesArticleType = servicesArticleType;
        this.servicesNewspaper = servicesNewspaper;
    }

    public void initialize(){
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    }

    @Override
    public void principalCargado() throws JAXBException, IOException {
        setComboBox();
    }

    private void setComboBox() {
        comboArticleType.getItems().addAll(servicesArticleType.getAllNameTypes());

        comboNewspapers.getItems().addAll(servicesNewspaper.getAll().stream().map(Newspaper::getNombre).toList());
    }

    @FXML
    private void showReaders() throws JAXBException, IOException {
        tableReaders.getItems().clear();
        tableReaders.getItems().addAll(servicesReaders.getReadersByArticleType(comboArticleType.getSelectionModel().getSelectedItem()));
    }

    @FXML
    private void showReadersByNewspaper() throws JAXBException, IOException {
        tableReaders.getItems().clear();
        tableReaders.getItems().addAll(servicesReaders.getReadersByNewspaper(comboNewspapers.getSelectionModel().getSelectedItem()));
    }
}
