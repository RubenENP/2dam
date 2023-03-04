package ui.screens.newspapers;

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
    private TableView<Newspaper> newspaperTable;
    @FXML
    private TableColumn<String, Newspaper> idNewspaperColumn;
    @FXML
    private TableColumn<String, Newspaper> nombreNewspaperColumn;
    @FXML
    private TableColumn<String, Newspaper> precioNewspaperColumn;
    @FXML
    private TableColumn<String, Newspaper> directorNewspaperColumn;

    ServicesNewspaper servicesNewspaper;

    @Inject
    DeleteNewspaperController(ServicesNewspaper servicesNewspaper){
        this.servicesNewspaper = servicesNewspaper;
    }

    public void initialize() {
        idNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        precioNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
        directorNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("director"));
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
                if(servicesNewspaper.deleteNewspaper(id)){
                    updateTable();
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("ERROR");
                    a.show();
                }
            }
        } else {
            if(servicesNewspaper.deleteNewspaper(id)){
                updateTable();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("ERROR");
                a.show();
            }
        }
    }

    private void updateTable() {
        newspaperTable.getItems().clear();
        newspaperTable.getItems().addAll(servicesNewspaper.getAll());
    }
}
