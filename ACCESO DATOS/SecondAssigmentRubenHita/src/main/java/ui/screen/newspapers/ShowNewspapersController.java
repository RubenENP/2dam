package ui.screen.newspapers;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Newspaper;
import services.ServicesNewspaper;
import ui.common.BaseScreenController;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ShowNewspapersController extends BaseScreenController {
    @FXML
    private TableView<Newspaper> newspaperTable;
    @FXML
    private TableColumn<Integer, Newspaper> idNewspaperColumn;
    @FXML
    private TableColumn<String, Newspaper> nameNewspaperColumn;
    @FXML
    private TableColumn<Date, Newspaper> releaseDateNewspaperColumn;

    ServicesNewspaper servicesNewspaper;

    @Inject
    ShowNewspapersController(ServicesNewspaper servicesNewspaper){
        this.servicesNewspaper = servicesNewspaper;
    }

    public void initialize(){
        idNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("name_newspaper"));
        releaseDateNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("release_date"));
    }

    @Override
    public void principalCargado() throws IOException {
        setTables();
    }

    private void setTables() {
        Either<List<Newspaper>, String> newspapers = servicesNewspaper.getAll();

        if(newspapers.isLeft()){
            newspaperTable.getItems().clear();
            newspaperTable.getItems().addAll(newspapers.getLeft());
        } else {
            showAlert(newspapers.get());
        }
    }

    private void showAlert(String m){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("ERROR "+m);
        a.show();
    }
}
