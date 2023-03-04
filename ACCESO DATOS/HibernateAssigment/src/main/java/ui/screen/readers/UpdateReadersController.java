package ui.screen.readers;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ReaderHib;
import services.ServicesReaders;
import ui.common.BaseScreenController;

import java.io.IOException;

public class UpdateReadersController extends BaseScreenController {
    public TextField newNameTxt;
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
    UpdateReadersController(ServicesReaders servicesReaders) {
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

    private void showAlert(String m, Alert.AlertType alertType) {
        Alert a = new Alert(alertType);
        a.setContentText(m);
        a.show();
    }

    public void setName() {
        Either<String, ReaderHib> update = servicesReaders.update(newNameTxt.getText(),
                tableReaders.getSelectionModel().getSelectedItem().getId(),
                tableReaders.getSelectionModel().getSelectedItem().getBirthReader().toString());

        if (update.isLeft()) {
            showAlert(update.getLeft(), Alert.AlertType.ERROR);
        } else {
            showAlert(update.get() + ", Updated.", Alert.AlertType.INFORMATION);
        }

        tableReaders.getItems().clear();
        tableReaders.getItems().addAll(servicesReaders.getAll().get());
    }
}
