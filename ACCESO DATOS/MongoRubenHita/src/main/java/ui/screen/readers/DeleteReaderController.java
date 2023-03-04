package ui.screen.readers;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Reader;
import services.ServicesReader;
import ui.common.BaseScreenController;

import java.io.IOException;
import java.util.List;

public class DeleteReaderController extends BaseScreenController {
    public TableView<Reader> readersTable;
    public TableColumn<String, Reader> nameColumn;
    public TableColumn<String, Reader> cancellationDateColumn;
    private final ServicesReader servicesReader;

    @Inject
    public DeleteReaderController(ServicesReader servicesReader) {
        this.servicesReader = servicesReader;
    }

    public void initialize(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        cancellationDateColumn.setCellValueFactory(new PropertyValueFactory<>("cancellationDate"));
    }

    @Override
    public void principalCargado() throws IOException, JAXBException {
        Either<String, List<Reader>> readers = servicesReader.getAll();
        if (readers.isLeft()){
            getPrincipalController().showAlert(Alert.AlertType.ERROR, readers.getLeft());
        } else {
            readersTable.getItems().addAll(servicesReader.getAll().get());
        }
    }

    public void delete() {

    }
}
