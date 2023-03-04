package ui.screen.readers;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Newspaper;
import model.Reader;
import services.ServicesNewspaper;
import services.ServicesReader;
import ui.common.BaseScreenController;

import java.io.IOException;
import java.util.List;

public class ShowReadersController extends BaseScreenController {

    public TableView<Reader> readersTable;
    public TableColumn<String, Reader> nameColumn;
    public TableColumn<String, Reader> cancellationDateColumn;
    public ComboBox<Newspaper> newspaperCombo;

    private final ServicesReader servicesReader;
    private final ServicesNewspaper servicesNewspaper;

    @Inject
    public ShowReadersController(ServicesReader servicesReader,  ServicesNewspaper servicesNewspaper) {
        this.servicesReader = servicesReader;
        this.servicesNewspaper = servicesNewspaper;
    }

    public void initialize(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        cancellationDateColumn.setCellValueFactory(new PropertyValueFactory<>("cancellationDate"));
    }

    @Override
    public void principalCargado() throws IOException, JAXBException {
        newspaperCombo.getItems().addAll(servicesNewspaper.getAll().get());
    }

    public void show() {
        Either<String, List<Reader>> readers = servicesReader.getAll(newspaperCombo.getSelectionModel().getSelectedItem());
        if (readers.isLeft()){
            getPrincipalController().showAlert(Alert.AlertType.ERROR, readers.getLeft());
        } else {
            readersTable.getItems().clear();
            readersTable.getItems().addAll(readers.get());
        }
    }
}
