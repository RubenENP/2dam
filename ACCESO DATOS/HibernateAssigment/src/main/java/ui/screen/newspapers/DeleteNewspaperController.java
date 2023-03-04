package ui.screen.newspapers;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.NewspaperHib;
import services.ServicesNewspaper;
import ui.common.BaseScreenController;

public class DeleteNewspaperController extends BaseScreenController {
    @FXML
    private TableColumn<String, NewspaperHib> releaseDateNewspaperColumn;
    @FXML
    private TableView<NewspaperHib> newspaperTable;
    @FXML
    private TableColumn<String, NewspaperHib> idNewspaperColumn;
    @FXML
    private TableColumn<String, NewspaperHib> nombreNewspaperColumn;

    ServicesNewspaper servicesNewspaper;

    @Inject
    DeleteNewspaperController(ServicesNewspaper servicesNewspaper){
        this.servicesNewspaper = servicesNewspaper;
    }

    public void initialize() {
        idNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("nameNewspaper"));
        releaseDateNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
    }

    @Override
    public void principalCargado() {
        updateTable();
    }

    public void deleteNewspaper() {
        NewspaperHib newspaperHib = newspaperTable.getSelectionModel().getSelectedItem();

        if(newspaperHib != null){
            Either<String, NewspaperHib> deleteNewspaper = servicesNewspaper.deleteNewspaper(newspaperHib);

            if (deleteNewspaper.isLeft()){
                showAlert(deleteNewspaper.getLeft(), Alert.AlertType.ERROR);
            } else {
                showAlert(deleteNewspaper.get().getNameNewspaper()+" deleted.", Alert.AlertType.INFORMATION);
                updateTable();
            }

        } else {
            showAlert("Select a newspaper", Alert.AlertType.ERROR);
        }
    }

    private void updateTable() {
        if(servicesNewspaper.getAll().isRight()){
            newspaperTable.getItems().clear();
            newspaperTable.getItems().addAll(servicesNewspaper.getAll().get());
        } else {
            showAlert(servicesNewspaper.getAll().getLeft(), Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String m, Alert.AlertType alertType){
        Alert a = new Alert(alertType);
        a.setContentText(m);
        a.show();
    }
}
