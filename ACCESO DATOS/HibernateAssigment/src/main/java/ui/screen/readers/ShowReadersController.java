package ui.screen.readers;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ArticleTypeHib;
import model.NewspaperHib;
import model.ReaderHib;
import services.ServicesArticleType;
import services.ServicesNewspaper;
import services.ServicesReadArticle;
import services.ServicesReaders;
import ui.common.BaseScreenController;

import java.util.List;
import java.util.Map;

public class ShowReadersController extends BaseScreenController {
    @FXML
    private ListView<Double> doubleRateColumn;
    @FXML
    private ListView<String> articleRateColumn;
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

    ServicesReadArticle servicesReadArticle;

    @Inject
    ShowReadersController(ServicesReaders servicesReaders, ServicesNewspaper servicesNewspaper,
                          ServicesArticleType servicesArticleType, ServicesReadArticle servicesReadArticle) {
        this.servicesReaders = servicesReaders;
        this.servicesNewspaper = servicesNewspaper;
        this.servicesArticleType = servicesArticleType;
        this.servicesReadArticle = servicesReadArticle;
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

        tableReaders.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            int readerId = newSelection.getId();

            Map<Double, String> map = servicesReadArticle.getAvgRating(readerId);

            articleRateColumn.getItems().clear();
            doubleRateColumn.getItems().clear();

            for (Map.Entry<Double, String> m : map.entrySet()){
                articleRateColumn.getItems().clear();
                doubleRateColumn.getItems().clear();

                articleRateColumn.getItems().add(m.getValue());
                doubleRateColumn.getItems().add(m.getKey());
            }
        });
    }

    private void setComboBox() {
        Either<String, List<NewspaperHib>> newspaperList = servicesNewspaper.getAll();

        if (newspaperList.isLeft()) {
            showAlert(newspaperList.getLeft());
        } else {
            newspapersComboBox.getItems().addAll(newspaperList.get().stream().map(NewspaperHib::getNameNewspaper).toList());
        }

        Either<String, List<ArticleTypeHib>> articleTypeList = servicesArticleType.getAll();

        if (articleTypeList.isLeft()) {
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

        if (readers.isLeft()) {
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

