package ui.screen.readers;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Newspaper;
import model.Reader;
import services.ServicesNewspaper;
import services.ServicesReader;
import ui.common.BaseScreenController;

import java.util.List;

public class AddReaderController extends BaseScreenController {
    private final ServicesReader servicesReader;
    private final ServicesNewspaper servicesNewspaper;
    public ComboBox<Newspaper> newspaperCombo;
    public TextField nameField;
    public DatePicker cancellationDate;

    @Inject
    public AddReaderController(ServicesReader servicesReader, ServicesNewspaper servicesNewspaper) {
        this.servicesReader = servicesReader;
        this.servicesNewspaper = servicesNewspaper;
    }

    @Override
    public void principalCargado() {
        newspaperCombo.getItems().clear();
        Either<String, List<Newspaper>> newspapers = servicesNewspaper.getAll();
        if (newspapers.isLeft()){
            getPrincipalController().showAlert(Alert.AlertType.ERROR, newspapers.getLeft());
        } else {
            newspaperCombo.getItems().addAll(newspapers.get());
        }
    }

    public void addReader() {
        Either<Integer, Reader> reader = servicesReader.save(nameField.getText(), cancellationDate.getValue().toString(),
                newspaperCombo.getSelectionModel().getSelectedItem());

        if (reader.isLeft()){
            getPrincipalController().showAlert(Alert.AlertType.ERROR, reader.getLeft().toString());
        } else {
            getPrincipalController().showAlert(Alert.AlertType.INFORMATION, reader.get().getName()+" added.");
        }
    }
}
