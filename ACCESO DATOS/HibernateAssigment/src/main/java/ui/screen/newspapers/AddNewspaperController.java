package ui.screen.newspapers;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.NewspaperHib;
import services.ServicesNewspaper;
import ui.common.BaseScreenController;

import java.io.IOException;
import java.sql.Date;


public class AddNewspaperController extends BaseScreenController {
    @FXML
    private TableColumn<String, NewspaperHib> releaseDateNewspaperColumn;
    @FXML
    private DatePicker releaseDatePicker;
    @FXML
    private TextField nombreTxtField;
    @FXML
    private TableColumn<String, NewspaperHib> idNewspaperColumn;
    @FXML
    private TableColumn<String, NewspaperHib> nombreNewspaperColumn;
    @FXML
    private TableView<NewspaperHib> newspaperTable;
    ServicesNewspaper servicesNewspaper;

    @Inject
    AddNewspaperController(ServicesNewspaper servicesNewspaper) {
        this.servicesNewspaper = servicesNewspaper;
    }

    public void initialize() {
        idNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("nameNewspaper"));
        releaseDateNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
    }

    @Override
    public void principalCargado() throws IOException {
        setTable();
    }

    private void setTable() {
        if (servicesNewspaper.getAll().isLeft()) {
            showAlert(servicesNewspaper.getAll().getLeft(), Alert.AlertType.ERROR);
        } else {
            newspaperTable.getItems().clear();
            newspaperTable.getItems().addAll(servicesNewspaper.getAll().get());
        }
    }

    @FXML
    private void addNewspaper() {
        Either<String, NewspaperHib> add = servicesNewspaper.addNewspaper(nombreTxtField.getText(),
                Date.valueOf(releaseDatePicker.getValue()));

        if (add.isLeft()) {
            showAlert(add.getLeft(), Alert.AlertType.ERROR);
        } else {
            setTable();
            showAlert(add.get().getNameNewspaper() + " added.", Alert.AlertType.INFORMATION);
        }
    }

    private void showAlert(String m, Alert.AlertType alertType) {
        Alert a = new Alert(alertType);
        a.setContentText(m);
        a.show();
    }
}
