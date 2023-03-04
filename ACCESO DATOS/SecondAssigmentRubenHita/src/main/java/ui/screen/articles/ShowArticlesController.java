package ui.screen.articles;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Article;
import model.ArticleType;
import services.ServicesArticleType;
import services.ServicesArticles;
import ui.common.BaseScreenController;

import java.util.List;

public class ShowArticlesController extends BaseScreenController {
    @FXML
    private CheckBox ratingCheckBox;
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private TableView<Article> articlesTable;
    @FXML
    private TableColumn<String, Article> idArticleColumn;
    @FXML
    private TableColumn<String, Article> titleArticleColumn;
    @FXML
    private TableColumn<String, Article> descriptionArticleColumn;
    @FXML
    private TableColumn<String, Article> idNewspaperArticleColumn;
    @FXML
    private TableColumn<String, Article> idTypeArticleColumn;

    ServicesArticles servicesArticles;

    ServicesArticleType servicesArticleType;

    @Inject
    ShowArticlesController(ServicesArticles servicesArticles, ServicesArticleType servicesArticleType) {
        this.servicesArticles = servicesArticles;
        this.servicesArticleType = servicesArticleType;
    }

    public void initialize() {
        idArticleColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleArticleColumn.setCellValueFactory(new PropertyValueFactory<>("name_article"));
        descriptionArticleColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        idNewspaperArticleColumn.setCellValueFactory(new PropertyValueFactory<>("id_newspaper"));
        idTypeArticleColumn.setCellValueFactory(new PropertyValueFactory<>("id_type"));
    }

    @Override
    public void principalCargado() {
        setComboBox();
    }

    private void setComboBox() {
        typeComboBox.getItems().clear();

        if (servicesArticleType.getAllNameTypes().isLeft()) {
            typeComboBox.getItems().addAll(servicesArticleType.getAllNameTypes().getLeft());
        } else {
            showAlert(servicesArticleType.getAllNameTypes().get());
        }

    }

    public void showArticlesByType() {
        articlesTable.getItems().clear();

        String typeDescription = typeComboBox.getSelectionModel().getSelectedItem();

        Either<List<Article>, String> articlesByType = servicesArticles.getArticlesByType(typeDescription);

        if (articlesByType.isLeft()) {
            articlesTable.getItems().addAll(articlesByType.getLeft());
        } else {
            showAlert(articlesByType.get());
        }
    }

    public void showByRating() {
        if (ratingCheckBox.isSelected()) {
            Either<List<Article>, String> articlesList = servicesArticles.getArticlesByRating();
            articlesTable.getItems().clear();

            if (articlesList.isLeft()){
                articlesTable.getItems().addAll(articlesList.getLeft());
            } else {
                showAlert(articlesList.get());
            }
        } else {
            showAlert("check box not selected");
        }
    }

    private void showAlert(String m) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("ERROR " + m);
        a.show();
    }
}
