package ui.screen.readers;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ReaderHib;
import model.Subscription;
import services.ServicesReaders;
import services.ServicesSubscriptions;
import ui.common.BaseScreenController;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DeleteReadersController extends BaseScreenController {
    @FXML
    private TableView<ReaderHib> tableReaders;
    @FXML
    private TableColumn<String, ReaderHib> columnId;
    @FXML
    private TableColumn<String, ReaderHib> columnName;
    @FXML
    private TableColumn<String, ReaderHib> columnBirthDate;

    private final ServicesReaders servicesReaders;
    private final ServicesSubscriptions servicesSubscriptions;

    @Inject
    DeleteReadersController(ServicesReaders servicesReaders, ServicesSubscriptions servicesSubscriptions) {
        this.servicesReaders = servicesReaders;
        this.servicesSubscriptions = servicesSubscriptions;
    }

    public void initialize() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("nameReader"));
        columnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthReader"));
    }

    @Override
    public void principalCargado() throws IOException, JAXBException {
        updateTable();
    }

    private void updateTable() {
        tableReaders.getItems().addAll(servicesReaders.getAll().get());
    }

    private void showAlert(String m, Alert.AlertType alertType) {
        Alert a = new Alert(alertType);
        a.setContentText(m);
        a.show();
    }

    @FXML
    private void delete() {

        if (!getPrincipalController().isAdminLogged()) {
            showAlert("You have to be admin", Alert.AlertType.ERROR);
        } else {
            Either<String, List<Subscription>> subscripcions = servicesSubscriptions
                    .get(tableReaders.getSelectionModel().getSelectedItem().getId());

            Subscription s = subscripcions.get().stream().filter(subscription ->
                    subscription.getCancellationDate().after(Date.valueOf(LocalDate.now()))).findFirst().orElse(null);

            if (s == null) {
                confirmDelete();
            } else {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setContentText("The reader has active subscriptions,\n Are you sure you want to delete it?");
                ButtonType buttonTypeConfirm = new ButtonType("Confirm");
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                a.getButtonTypes().setAll(buttonTypeConfirm, buttonTypeCancel);
                Optional<ButtonType> result = a.showAndWait();
                if(result.get() == buttonTypeConfirm){
                    confirmDelete();
                } else {
                    a.close();
                }
            }
        }
    }

    private void confirmDelete() {
        Either<String, ReaderHib> deletedReader = servicesReaders.delete(tableReaders.getSelectionModel().getSelectedItem());
        if (deletedReader.isLeft()) {
            showAlert(deletedReader.getLeft(), Alert.AlertType.ERROR);
        } else {
            showAlert(deletedReader.get().getNameReader() + " deleted.", Alert.AlertType.INFORMATION);
            updateTable();
        }
    }
}
