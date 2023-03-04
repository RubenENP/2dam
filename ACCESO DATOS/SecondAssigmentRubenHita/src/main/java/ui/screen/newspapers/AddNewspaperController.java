package ui.screen.newspapers;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Newspaper;
import services.ServicesNewspaper;
import ui.common.BaseScreenController;

import java.io.IOException;
import java.sql.Date;


public class AddNewspaperController extends BaseScreenController {
    @FXML
    private TableColumn<String, Newspaper> releaseDateNewspaperColumn;
    @FXML
    private DatePicker releaseDatePicker;
    @FXML
    private TextField nombreTxtField;
    @FXML
    private TableColumn<String, Newspaper> idNewspaperColumn;
    @FXML
    private TableColumn<String, Newspaper> nombreNewspaperColumn;
    @FXML
    private TableView<Newspaper> newspaperTable;
    ServicesNewspaper servicesNewspaper;

    @Inject
    AddNewspaperController(ServicesNewspaper servicesNewspaper){
        this.servicesNewspaper = servicesNewspaper;
    }

    public void initialize(){
        idNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("name_newspaper"));
        releaseDateNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("release_date"));
    }

    @Override
    public void principalCargado() throws IOException {
        setTable();
    }

    private void setTable() {
        if (servicesNewspaper.getAll().isLeft()){
            newspaperTable.getItems().clear();
            newspaperTable.getItems().addAll(servicesNewspaper.getAll().getLeft());
        } else {
            showAlert(servicesNewspaper.getAll().get(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void addNewspaper() {
        Newspaper n = servicesNewspaper.addNewspaper(nombreTxtField.getText(), Date.valueOf(releaseDatePicker.getValue()));
        if(n != null){
            setTable();
            showAlert(n.getName_newspaper()+" added.", Alert.AlertType.INFORMATION);
        }else {
            showAlert("ERROR", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String m, Alert.AlertType alertType) {
        Alert a = new Alert(alertType);
        a.setContentText(m);
        a.show();
    }
}
