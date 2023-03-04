package ui.screen.readers;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Newspaper;
import model.Reader;
import services.ServicesNewspaper;
import services.ServicesReader;
import ui.common.BaseScreenController;
import ui.common.Screens;

import java.io.IOException;
import java.util.List;


public class UpdateReadersController extends BaseScreenController {
    public TableView<Reader> readersTable;
    public TableColumn nameColumn;
    public TableColumn cancellationDateColumn;
    public TextField nameField;
    public DatePicker datePickerCancellation;

    private final ServicesReader servicesReader;
    public TableView<Newspaper> newspaperTable;
    public TableColumn<String, Newspaper> idNewspaperColumn;
    public TableColumn<String, Newspaper> nameNewspaperColumn;
    public TableColumn<String, Newspaper> releaseDateNewspaperColumn;

    ServicesNewspaper servicesNewspaper;

    @Inject
    public UpdateReadersController(ServicesReader servicesReader, ServicesNewspaper servicesNewspaper) {
        this.servicesReader = servicesReader;
        this.servicesNewspaper = servicesNewspaper;
    }

    public void initialize(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        cancellationDateColumn.setCellValueFactory(new PropertyValueFactory<>("cancellationDate"));

        idNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("_id"));
        nameNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        releaseDateNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("relDate"));
    }

    @Override
    public void principalCargado() throws IOException, JAXBException {
        Either<String, List<Newspaper>> newspapers = servicesNewspaper.getAll();

        newspaperTable.getItems().addAll(newspapers.get());

        newspaperTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldNewspaper, newNewspaper) -> {
            readersTable.getItems().clear();
            readersTable.getItems().addAll(newNewspaper.getReaders());
        });
    }

    public void update() {
        String date = datePickerCancellation.getValue().getYear()+"-"+datePickerCancellation.getValue()
                .getMonthValue()+"-"+datePickerCancellation.getValue().getDayOfMonth();
        Reader r = new Reader(readersTable.getSelectionModel().getSelectedItem().get_id(), nameField.getText(), date);
        servicesReader.update(r, newspaperTable.getSelectionModel().getSelectedItem(), readersTable.getSelectionModel().getSelectedItem().get_id());
    }
}
