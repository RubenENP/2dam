package ui.screen.newspapers;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Newspaper;
import services.ServicesNewspaper;
import ui.common.BaseScreenController;

import java.util.Optional;

public class DeleteNewspaperController extends BaseScreenController {
    @FXML
    private TableColumn<String, Newspaper> releaseDateNewspaperColumn;
    @FXML
    private TableView<Newspaper> newspaperTable;
    @FXML
    private TableColumn<String, Newspaper> idNewspaperColumn;
    @FXML
    private TableColumn<String, Newspaper> nombreNewspaperColumn;

    ServicesNewspaper servicesNewspaper;

    @Inject
    DeleteNewspaperController(ServicesNewspaper servicesNewspaper){
        this.servicesNewspaper = servicesNewspaper;
    }

    public void initialize() {
        idNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("name_newspaper"));
        releaseDateNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("release_date"));
    }

    @Override
    public void principalCargado() {
        updateTable();
    }

    public void deleteNewspaper() {
        int id = newspaperTable.getSelectionModel().getSelectedItem().getId();

        if (servicesNewspaper.checkArticles(id)){
            Alert areYouSure = new Alert(Alert.AlertType.CONFIRMATION);
            areYouSure.setContentText("This newspaper contains articles.\nAre you sure you want to delete?");
            Optional<ButtonType> action = areYouSure.showAndWait();

            if (action.get() == ButtonType.OK){
                Newspaper n = servicesNewspaper.deleteNewspaper(id);

                if(n!=null){
                    showAlert(n.getName_newspaper()+" deleted.", Alert.AlertType.INFORMATION);
                    updateTable();
                } else {
                    showAlert("ERROR", Alert.AlertType.ERROR);
                }
            }
        } else {
            Newspaper n = servicesNewspaper.deleteNewspaper(id);

            if(n!=null){
                showAlert(n.getName_newspaper()+" deleted.", Alert.AlertType.INFORMATION);
                updateTable();
            } else {
                showAlert("ERROR", Alert.AlertType.ERROR);
            }
        }
    }

    private void updateTable() {
        if(servicesNewspaper.getAll().isLeft()){
            newspaperTable.getItems().clear();
            newspaperTable.getItems().addAll(servicesNewspaper.getAll().getLeft());
        } else {
            showAlert(servicesNewspaper.getAll().get(), Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String m, Alert.AlertType alertType){
        Alert a = new Alert(alertType);
        a.setContentText(m);
        a.show();
    }
}
