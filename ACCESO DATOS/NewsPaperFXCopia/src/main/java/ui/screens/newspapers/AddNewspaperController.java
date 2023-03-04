package ui.screens.newspapers;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Newspaper;
import services.ServicesNewspaper;
import ui.common.BaseScreenController;

import java.io.IOException;


public class AddNewspaperController extends BaseScreenController {
    @FXML
    private TextField idTxtField;
    @FXML
    private TextField nombreTxtField;
    @FXML
    private TextField precioTxtField;
    @FXML
    private TextField directorTxtField;
    @FXML
    private TableColumn<String, Newspaper> idNewspaperColumn;
    @FXML
    private TableColumn<String, Newspaper> nombreNewspaperColumn;
    @FXML
    private TableColumn<String, Newspaper> precioNewspaperColumn;
    @FXML
    private TableColumn<String, Newspaper> directorNewspaperColumn;
    @FXML
    private TableView<Newspaper> newspaperTable;
    ServicesNewspaper servicesNewspaper;

    @Inject
    AddNewspaperController(ServicesNewspaper servicesNewspaper){
        this.servicesNewspaper = servicesNewspaper;
    }

    public void initialize(){
        idNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        precioNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
        directorNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("director"));
    }

    @Override
    public void principalCargado() throws IOException {
        setTable();
    }

    private void setTable() {
        newspaperTable.getItems().clear();
        newspaperTable.getItems().addAll(servicesNewspaper.getAll());
    }

    @FXML
    private void addNewspaper() throws IOException {
        if(servicesNewspaper.addNewspaper(idTxtField.getText(), nombreTxtField.getText(), precioTxtField.getText(), directorTxtField.getText())){
            setTable();
        }else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("ERROR");
            a.show();
        }


    }
}
