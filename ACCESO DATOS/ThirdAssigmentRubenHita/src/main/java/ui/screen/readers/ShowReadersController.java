package ui.screen.readers;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ArticleTypeHib;
import model.NewspaperHib;
import model.ReaderHib;
import services.ServicesArticleType;
import services.ServicesNewspaper;
import services.ServicesReaders;
import ui.common.BaseScreenController;

import java.util.List;

public class ShowReadersController extends BaseScreenController {
    @FXML
    private ComboBox<String> articleTypesComboBox;
    @FXML
    private ComboBox<String> newspapersComboBox;
    @FXML
    private TableView<ReaderHib> tableReaders;
    @FXML
    private TableColumn<String, ReaderHib> columnId;
    @FXML
    private TableColumn<String, ReaderHib> columnName;
    @FXML
    private TableColumn<String, ReaderHib> columnBirthDate;

    ServicesReaders servicesReaders;
    ServicesNewspaper servicesNewspaper;
    ServicesArticleType servicesArticleType;

    @Inject
    ShowReadersController(ServicesReaders servicesReaders, ServicesNewspaper servicesNewspaper,
                          ServicesArticleType servicesArticleType) {
        this.servicesReaders = servicesReaders;
        this.servicesNewspaper = servicesNewspaper;
        this.servicesArticleType = servicesArticleType;
    }

    public void initialize() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("nameReader"));
        columnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthReader"));
    }

    @Override
    public void principalCargado() {
        tableReaders.getItems().addAll(servicesReaders.getAll().get());
        setComboBox();
    }

    private void setComboBox() {
        Either<String, List<NewspaperHib>> newspaperList = servicesNewspaper.getAll();

        if (newspaperList.isLeft()) {
            showAlert(newspaperList.getLeft());
        } else {
            newspapersComboBox.getItems().addAll(newspaperList.get().stream().map(NewspaperHib::getNameNewspaper).toList());
        }

        Either<String, List<ArticleTypeHib>> articleTypeList = servicesArticleType.getAll();

        if (articleTypeList.isLeft()){
            showAlert(articleTypeList.getLeft());
        } else {
            articleTypesComboBox.getItems().addAll(articleTypeList.get().stream().map(ArticleTypeHib::getDescription).toList());
        }
    }

    private void showAlert(String m) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("ERROR " + m);
        a.show();
    }

    public void showReadersByNewspaper() {
        String name = newspapersComboBox.getSelectionModel().getSelectedItem();
        Either<String, List<ReaderHib>> readers = servicesReaders.getAllByNewspaper(name);

        if (readers.isLeft()){
            showAlert(readers.getLeft());
        } else {
            tableReaders.getItems().clear();
            tableReaders.getItems().addAll(readers.get());
        }
    }

    public void showReadersByArticleType() {
        String articleTypeName = articleTypesComboBox.getSelectionModel().getSelectedItem();

        Either<String, List<ReaderHib>> readers = servicesReaders.getReaderOfAnArticleType(articleTypeName);

        tableReaders.getItems().clear();
        tableReaders.getItems().addAll(readers.get());
    }
}

