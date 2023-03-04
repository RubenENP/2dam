package ui.screen.readers;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ArticleType;
import model.Newspaper;
import model.reader.Reader;
import services.ServicesArticleType;
import services.ServicesNewspaper;
import services.ServicesReaders;
import ui.common.BaseScreenController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShowReadersController extends BaseScreenController {
    @FXML
    private ComboBox<String> articleTypesComboBox;
    @FXML
    private ComboBox<String> newspapersComboBox;
    @FXML
    private TableView<Reader> tableReaders;
    @FXML
    private TableColumn<String, Reader> columnId;
    @FXML
    private TableColumn<String, Reader> columnName;
    @FXML
    private TableColumn<String, Reader> columnBirthDate;

    ServicesReaders servicesReaders;
    ServicesNewspaper servicesNewspaper;
    ServicesArticleType servicesArticleType;

    @Inject
    ShowReadersController(ServicesReaders servicesReaders, ServicesNewspaper servicesNewspaper,
                          ServicesArticleType servicesArticleType){
        this.servicesReaders = servicesReaders;
        this.servicesNewspaper = servicesNewspaper;
        this.servicesArticleType = servicesArticleType;
    }

    public void initialize(){
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    }

    @Override
    public void principalCargado() {
        setComboBox();
    }

    private void setComboBox() {
        Either<List<Newspaper>, String> newspaperList = servicesNewspaper.getAll();

        if (newspaperList.isLeft()){
            List<String> allNewspaperNames = new ArrayList<>(newspaperList.getLeft().stream().map(Newspaper::getName_newspaper).toList());
            newspapersComboBox.getItems().addAll(allNewspaperNames);
        }else {
            showAlert(newspaperList.get());
        }

        Either<List<String>, String> articleTypesList = servicesArticleType.getAllNameTypes();

        if (articleTypesList.isLeft()){
            articleTypesComboBox.getItems().addAll(articleTypesList.getLeft());
        } else {
            showAlert(articleTypesList.get());
        }
    }

    private void showAlert(String m) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("ERROR "+m);
        a.show();
    }

    public void showReadersByNewspaper() {
        String newspaperName = newspapersComboBox.getSelectionModel().getSelectedItem();
        Either<List<Newspaper>, String> newspaperList = servicesNewspaper.getAll();

        if (newspaperList.isLeft()){
            int id = Objects.requireNonNull(newspaperList.getLeft().stream()
                    .filter(newspaper -> newspaper.getName_newspaper().equals(newspaperName))
                    .findFirst().orElse(null)).getId();

            Either<List<Reader>, String> readers = servicesReaders.getAllByNewspaperId(id);

            if (readers.isLeft()){
                tableReaders.getItems().clear();
                tableReaders.getItems().addAll(readers.getLeft());
            }else {
                showAlert(readers.get());
            }
        } else {
            showAlert(newspaperList.get());
        }
    }

    public void showReadersByArticleType() {
        String articleTypeName = articleTypesComboBox.getSelectionModel().getSelectedItem();
        Either<List<ArticleType>, String> articleTypeList = servicesArticleType.getAllTypes();

        if (articleTypeList.isLeft()){
            int id = articleTypeList.getLeft().stream().filter(articleType -> articleTypeName.equals(articleType.getName()))
                    .findFirst().orElse(null).getId();

            Either<List<Reader>, String> readers = servicesReaders.getAllByArticleTypeId(id);

            tableReaders.getItems().clear();
            tableReaders.getItems().addAll(readers.getLeft());
        }

    }
}
