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

import java.sql.Date;
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

    private final ServicesNewspaper servicesNewspaper;

    @Inject
    ShowNewspapersController(ServicesNewspaper servicesNewspaper){
        this.servicesNewspaper = servicesNewspaper;
    }

    public void initialize(){
        idNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("_id"));
        nameNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        releaseDateNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("relDate"));
    }

    @Override
    public void principalCargado() {
        setTables();
    }

    private void setTables() {
        Either<String, List<Newspaper>>  newspapers = servicesNewspaper.getAll();

        if(newspapers.isLeft()){
            showAlert(newspapers.getLeft());
        } else {
            newspaperTable.getItems().clear();
            newspaperTable.getItems().addAll(newspapers.get());
        }
    }

    private void showAlert(String m){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("ERROR "+m);
        a.show();
    }

}
