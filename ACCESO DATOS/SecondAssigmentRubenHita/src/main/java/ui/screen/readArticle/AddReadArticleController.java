package ui.screen.readArticle;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;
import model.readArticle.ReadArticle;
import services.ServicesArticles;
import services.ServicesReadArticle;
import services.ServicesReaders;
import ui.common.BaseScreenController;

import java.io.IOException;
import java.util.List;

@Log4j2
public class AddReadArticleController extends BaseScreenController {
    @FXML
    private TableView<ReadArticle> readArticlesTable;
    @FXML
    private ComboBox<String> comboArticle;
    @FXML
    private TextField textRating;
    @FXML
    private TableColumn<String, ReadArticle> idColumn;
    @FXML
    private TableColumn<String, ReadArticle> idReaderColumn;
    @FXML
    private TableColumn<String, ReadArticle> idArticleColumn;
    @FXML
    private TableColumn<String, ReadArticle> ratingColumn;

    ServicesReadArticle servicesReadArticle;
    ServicesReaders servicesReaders;
    ServicesArticles servicesArticles;

    @Inject
    AddReadArticleController(ServicesReadArticle servicesReadArticle, ServicesReaders servicesReaders, ServicesArticles servicesArticles){
        this.servicesReadArticle = servicesReadArticle;
        this.servicesReaders = servicesReaders;
        this.servicesArticles = servicesArticles;
    }

    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idReaderColumn.setCellValueFactory(new PropertyValueFactory<>("idReader"));
        idArticleColumn.setCellValueFactory(new PropertyValueFactory<>("idArticle"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("ranking"));
    }

    @Override
    public void principalCargado() {
        setItems();
    }

    private void setItems() {
        Either<List<String>, String> allArticlesNames = servicesArticles.getAllArticlesName();

        if (allArticlesNames.isLeft()){
            comboArticle.getItems().clear();
            comboArticle.getItems().addAll(allArticlesNames.getLeft());
        } else {
            showAlert(allArticlesNames.get());
        }

        Either<List<ReadArticle>, String> allArticleTypes = servicesReadArticle.getAll();

        if (allArticleTypes.isLeft()){
            readArticlesTable.getItems().clear();
            readArticlesTable.getItems().addAll(servicesReadArticle.getAll().getLeft());
        } else {
            showAlert(allArticleTypes.get());
        }
    }

    private void showAlert(String m) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("ERROR "+m);
        a.show();
    }

    @FXML
    private void addReadArticle() {
        servicesReadArticle.addReadArticle(getPrincipalController().getActualUser(), comboArticle.getSelectionModel().getSelectedItem(), Integer.parseInt(textRating.getText()));

        setItems();
    }
}
